#####本项目基于TransferWise Bootcamp测试任务，模拟了一个真实的国际银行转账系统。

####该系统主要包括如下几个功能：

1. 国内账户转账：
从服务器获取json数据，根据银行名称、源账户号码、目的账户号码以及转账金额等参数，瞬时进行大量账户的自动转账
2. 国际间账户转账：
从服务器获取json数据，根据银行名称、源账户号码、目的账户号码、银行ID、币种、汇率以及转账金额等参数，瞬时进行大量账户的自动转账
3. 虚假金额分析
从服务器获取json数据，服务器将会给出几家不同的国际转账公司的实际转账数据，包括币种、金额、银行账户、实时汇率等信息，使用程序从大量的信息中找出那些公司收取了额外的费用，以及额外费用的数量
4. 敏感信息检索
从服务器获取json数据，在大量的转账信息中找出一些重要政治人物的交易信息，并将其单独标识
5. 欺诈交易检索
从服务器获取json数据，在大量的交易历史中根据提示信息找出欺诈交易账户信息的模式，并将所有的欺诈交易标识出来

###程序实现：

####说明：

1. 开发环境：该项目使用Java程序开发，使用maven作为项目管理工具，使用Eclipse集成开发环境；
2. 该系统会从使用restful与服务器端进行各种数据的交互，数据以json的形式返回；

####程序说明：

- com.solutions – 这个包中包含针对以上各个功能所做的解决方案；
- com.beans – 这个包中所包含的bean是用来接受json数据的，通过JsonParser类中的方法可以将json直接转为java bean然后供数据分析使用；
- com.util –这是一个工具类包，其中的HttpRequest类使用了java.net.*包，用于向服务器发送GET, PUT, POST, DELETE请求；JsonParser类用于java bean与json数据之间的转换，其功能使用json-lib来实现；FormatString用于对返回URL的格式化。
- com.test – 这个包中的类仅用于测试我的想法，可以忽略