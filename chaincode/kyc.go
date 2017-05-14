package main

import (
	"encoding/json"
	"errors"
	"fmt"

	"github.com/hyperledger/fabric/core/chaincode/shim"
)

type KycChaincode struct {
}

const (
	roleAttr    = "role"
	role_reader = "reader"
	role_writer = "writer"
)

//User information
type UserInfo struct {
	Name     string `json:"name"`
	IDNum    string `json:"idNum,omitempty"`
	PhoneNum string `json:"phoneNum,omitempty"`
	Addr     string `json:"addr,omitempty"`
}

func (t *KycChaincode) Init(stub shim.ChaincodeStubInterface, function string, args []string) ([]byte, error) {
	if len(args) != 1 {
		return []byte(`{"code":-1,"reason":"1 arguments writerRole needed"}`), nil
	}

	writerRole := args[0]
	fmt.Printf("assigner role is %v\n", string(writerRole))

	if len(writerRole) == 0 {
		return nil, errors.New("Invalid assigner role. Empty")
	}

	stub.PutState("writerRole", []byte(writerRole))

	return nil, nil
}

func (t *KycChaincode) Invoke(stub shim.ChaincodeStubInterface, function string, args []string) ([]byte, error) {
	if function == "putCustomerInfo" {
		return putCustomerInfo(stub, args)
	} else if function == "putDetailInfo" {
		return putDetailInfo(stub, args)
	}
	return nil, fmt.Errorf("query function:[%v] does not support. Expected putCustomerInfo", function)
}

func (t *KycChaincode) Query(stub shim.ChaincodeStubInterface, function string, args []string) (res []byte, err error) {

	if function == "queryCustomerInfo" {
		return queryCustomerInfo(stub, args)
	} else if function == "queryDetailInfo" {
		return queryDetailInfo(stub, args)
	}
	return nil, fmt.Errorf("query function:[%v] does not support. Expected queryCustomerInfo", function)
}

//putCustomerInfo collect customer infomation
func putCustomerInfo(stub shim.ChaincodeStubInterface, args []string) ([]byte, error) {
	if len(args) != 2 {
		return []byte(`{"code":-1,"reason":"2 arguments needed"}`), nil
	}

	isWriter, callerRole, err := canWriter(stub)
	if err != nil {
		fmt.Printf("Failed getting caller role. [%v] \n", err)
		return nil, fmt.Errorf("Failed getting caller role. [%v] \n", err)
	}

	if !isWriter {
		fmt.Printf("Caller is not writer - caller %v writer %v\n", callerRole, role_writer)
		return nil, fmt.Errorf("The caller does not have the rights to invoke writer. Expected role[%v],caller role[%v]", role_writer, callerRole)
	}

	uid := args[0]
	userInfo := args[1]

	err = stub.PutState(uid, []byte(userInfo))
	if err != nil {
		return []byte(`{"code":-1}`), err
	}
	return []byte(`{"code":0}`), nil
}

//putDetailInfo collect customer detail information
func putDetailInfo(stub shim.ChaincodeStubInterface, args []string) ([]byte, error) {
	if len(args) != 2 {
		return []byte(`{"code":-1,"reason":"2 arguments needed"}`), nil
	}

	isWriter, callerRole, err := canWriter(stub)
	if err != nil {
		fmt.Printf("Failed getting caller role. [%v] \n", err)
		return nil, fmt.Errorf("Failed getting caller role. [%v] \n", err)
	}

	if !isWriter {
		fmt.Printf("Caller is not writer - caller %v writer %v\n", callerRole, role_writer)
		return nil, fmt.Errorf("The caller does not have the rights to invoke writer. Expected role[%v],caller role[%v]", role_writer, callerRole)
	}

	uid := args[0]
	userInfo := args[1]

	//Parse the userInfo
	var user UserInfo
	err = json.Unmarshal([]byte(userInfo), &user)
	if err != nil {
		fmt.Printf("Failed parsing user infomation. %v\n", err)
		return nil, fmt.Errorf("Faild parsing user information. %v\n", err)
	}

	//TODO verify the detail information one by one

	err = stub.PutState(uid, []byte(userInfo))
	if err != nil {
		return []byte(`{"code":-1}`), err
	}
	return []byte(`{"code":0}`), nil
}

//queryCustomerInfo query all customer information
//The arguments should be user id
func queryCustomerInfo(stub shim.ChaincodeStubInterface, args []string) ([]byte, error) {
	if len(args) != 1 {
		return []byte(fmt.Sprintf(`{"code":-1,"reason":"1 arguments needed, while has %v"}`, len(args))), nil
	}

	isReader, callerRole, err := canRead(stub)
	if err != nil {
		return nil, err
	}

	if !isReader {
		fmt.Printf("Caller is not a reader - caller %v \n", callerRole)
		return nil, fmt.Errorf("The caller does not have the rights to invoke reader. Expected role[%v],caller role[%v]", role_reader, callerRole)
	}

	uid := args[0]

	info, err := stub.GetState(uid)
	return info, err
}

//queryDetailInfo query customer information withe detail customer attribute
//The arguments should be user id and customer attribute name,
//If the caller wants to get all the customer information, the arguments should be user id and "all"
func queryDetailInfo(stub shim.ChaincodeStubInterface, args []string) ([]byte, error) {
	if len(args) != 2 {
		return []byte(fmt.Sprintf(`{"code":-1,"reason":"2 arguments needed, while has %v"}`, len(args))), nil
	}

	isReader, callerRole, err := canRead(stub)
	if err != nil {
		return nil, err
	}

	if !isReader {
		fmt.Printf("Caller is not a reader - caller %v \n", callerRole)
		return nil, fmt.Errorf("The caller does not have the rights to invoke reader. Expected role[%v],caller role[%v]", "reader", callerRole)
	}

	//Get customer information and the attribute name
	uid := args[0]
	attrName := args[1]
	info, err := stub.GetState(uid)
	if err != nil {
		fmt.Printf("Failed getting customer information. %v\n", err)
		return nil, fmt.Errorf("Failed getting customer information. %v\n", err)
	}
	//Parse customer information.
	var user UserInfo
	err = json.Unmarshal(info, &user)
	if err != nil {
		fmt.Errorf("Failed parsing customer information. %v, info:[%v]\n", err, info)
		return nil, fmt.Errorf("Failed parsing customer information. %v\n", err)
	}

	switch attrName {
	case "name":
		return []byte(user.Name), nil
	case "idNum":
		return []byte(user.IDNum), nil
	case "phone":
		return []byte(user.PhoneNum), nil
	case "addr":
		return []byte(user.Addr), nil
	case "all":
		return info, nil
	default:
		return nil, fmt.Errorf("Customer doesn't have attribute %s", attrName)
	}
}

//canWriter check a specified caller can write the customer information
func canWriter(stub shim.ChaincodeStubInterface) (bool, string, error) {
	writerRole, err := stub.GetState("writerRole")
	if err != nil {
		fmt.Printf("Error getting role [%v] \n", err)
		return false, "", errors.New("failed fetching writer role")
	}

	callerRole, err := stub.ReadCertAttribute("role")
	if err != nil {
		fmt.Printf("Error reading attribute role[%v]\n", err)
		return false, "", fmt.Errorf("Failed fetching caller role. Error was [%v]", err)
	}

	caller := string(callerRole[:])
	writer := string(writerRole[:])

	if caller == writer {
		return true, caller, nil
	} else {
		return false, caller, nil
	}

}

//canRead check a specified caller can read the customer information
//bool whether can read
//string the actual role
func canRead(stub shim.ChaincodeStubInterface) (bool, string, error) {
	callerRole, err := stub.ReadCertAttribute(roleAttr)
	if err != nil {
		fmt.Printf("Error reading attribute role[%v]\n", err)
		return false, "", fmt.Errorf("Failed fetching caller role. Error was [%v]", err)
	}
	role := string(callerRole[:])
	if role == role_reader {
		return true, role, nil
	} else {
		return false, role, nil
	}
}

func main() {
	err := shim.Start(new(KycChaincode))
	if err != nil {
		fmt.Printf("Error starting Simple chaincode:%s", err)
	}
}
