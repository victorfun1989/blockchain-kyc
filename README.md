# blockchain-kyc
A blockchain KYC project based on hyperledger/fabric

this is a project created on hyperledger hackathon by cibfintech

## 理念
这个项目基于以下理念创建:

- 用户信息来源的可追溯性
- 用户信息查看需要获得授权

除了应用于KYC领域之外，还可以应用到其它领域，如反欺诈信息共享、企业黑名单信息共享等

## 原理
基于hyperledger fabric v0.6创建，参与的各方组成一个联盟链，使用智能合约来控制用户信息的读写权限。
区块链上存储的信息都是经过用户公钥加密，用户使用私钥来授权访问。
