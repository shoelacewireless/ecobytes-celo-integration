# ecobytes-wallet-integration
## Library to connect to Valora

ecobytes-wallet-integration is a library that allows you to connect easily to Valora wallet or Alfajores Wallet. For now it only includes a call to get user public address so tokens can be transferred to it.

- No need for WalletConnect Library
- Uses Java intent

## Features

- Example project to connect to Valora using intent.
- Includes calls to redeem EcoBytes points for Celo tokens(Requires integration to shoelace cloud)

## Installation

Add EcoBytesSDK to your project.

Add the following intent to your application manifest. Test phone needs to have a valora or alfajores wallet installed.

```java
<intent-filter android:label="EcoBytesSDK">
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="ecobytessdk"
            android:host="res" />
    </intent-filter>
```

If using shoelace cloud make sure to replace server dummy file in SDK for server certificate provided by shoelace.

## License

    Copyright 2022 Shoelace Wireless

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.