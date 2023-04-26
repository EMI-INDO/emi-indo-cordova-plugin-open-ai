# emi-indo-cordova-plugin-open-ai
 Cordova Plugin for using OpenAI's GPT apis. Supports GPT-3, ChatGPT, and GPT-4. ( Requires third party services https://openai.com/ that charge money )


> - [Code source:](https://platform.openai.com/docs/api-reference/introduction) - OpenAI:

## 💰Sponsor this project
  [![PayPal](https://img.shields.io/badge/PayPal-00457C?style=for-the-badge&logo=paypal&logoColor=white)](https://paypal.me/emiindo)  
  
  
  ## Features
- Completions ( model: text-davinci-003 )


 ## Coming soon Features
- chat completion ( model: gpt-3.5-turbo )
- edit ( model: text-davinci-edit-001 )
- Images generations 


## Installation

```sh
cordova plugin add emi-indo-cordova-plugin-open-ai
```
### Or
```sh
cordova plugin add https://github.com/EMI-INDO/emi-indo-cordova-plugin-open-ai
```

## Remove
```sh
cordova plugin rm emi-indo-cordova-plugin-open-ai

```


## Completions


```sh

let _getCompletionsRequest = () => {
    cordova.plugins.emiOpenAiPlugin.completions(
    message = "Hi? make me html code", // string
    apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",  // apiKey https://platform.openai.com/

    (result) => { alert(result) },
    (error) => { alert(error)

    });
}

// call _getCompletionsRequest();


```

## result
<img width="393" alt="2023-04-26_171947" src="https://user-images.githubusercontent.com/78555833/234546797-50c81ecc-9044-4316-8abf-a2f5afc2c7d0.png">


