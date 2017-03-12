package main

import (
    "errors"
    //"encoding/json"
    "fmt"
    "github.com/hyperledger/fabric/core/chaincode/shim"
)

type KycChaincode struct {
}

func (t *KycChaincode) Init(stub shim.ChaincodeStubInterface, function string, args []string) ([]byte, error) {
    if len(args) != 1 {
        return []byte(`{"code":-1,"reason":"1 arguments writerRole needed"}`), nil
    } 

    writerRole := args[0]
    fmt.Printf("assigner role is %v\n",string(writerRole))

    if len(writerRole) == 0{
        return nil, errors.New("Invalid assigner role. Empty")
    }

    stub.PutState("writerRole",[]byte(writerRole));

    return nil, nil
}

func (t *KycChaincode) Invoke(stub shim.ChaincodeStubInterface, function string, args []string) ([]byte, error) { 
    if function == "putCustomerInfo" {
        if len(args) != 2 {
            return []byte(`{"code":-1,"reason":"2 arguments needed"}`), nil
        }

        writerRole,err := stub.GetState("writerRole");
        if err != nil {
            fmt.Printf("Error getting role [%v] \n",err)
            return nil, errors.New("failed fetching writer role")
        }

        callerRole, err := stub.ReadCertAttribute("role");
        if err != nil {
            fmt.Printf("Error reading attribute role[%v]\n",err)
            return nil,fmt.Errorf("Failed fetching caller role. Error was [%v]",err)
        }

        caller := string(callerRole[:])
        writer := string(writerRole[:])

        if caller != writer {
            fmt.Printf("Caller is not writer - caller %v writer %v\n",caller, writer)
            return nil, fmt.Errorf("The caller does not have the rights to invoke writer. Expected role[%v],caller role[%v]",writer,caller)
        } 

        uid := args[0]
        userInfo := args[1]

        err = stub.PutState(uid, []byte(userInfo))
        if err != nil {
            return []byte(`{"code":-1}`), err
        } 
        return []byte(`{"code":0}`),nil 
    } 
    return nil, fmt.Errorf("query function:[%v] does not support. Expected putCustomerInfo",function)
}

func (t *KycChaincode) Query(stub shim.ChaincodeStubInterface, function string, args []string) (res []byte, err error) {

    if function == "queryCustomerInfo" {
        if len(args) != 1 {
            return []byte(fmt.Sprintf(`{"code":-1,"reason":"1 arguments needed, while has %v"}`, len(args))), nil
        }

        uid := args[0]

        callerRole, err := stub.ReadCertAttribute("role");
        if err != nil {
            fmt.Printf("Error reading attribute role[%v]\n",err)
            return nil,fmt.Errorf("Failed fetching caller role. Error was [%v]",err)
        }
        caller := string(callerRole[:])

        if caller != "reader" {
            fmt.Printf("Caller is not reader - caller %v \n",caller)
            return nil, fmt.Errorf("The caller does not have the rights to invoke reader. Expected role[%v],caller role[%v]","reader",caller)
        } 

        info, err := stub.GetState(uid)
        return info,err 
    }
    return nil, fmt.Errorf("query function:[%v] does not support. Expected queryCustomerInfo",function)
}

func main() {
    err := shim.Start(new(KycChaincode))
    if err != nil {
        fmt.Printf("Error starting Simple chaincode:%s",err);
    }
}

