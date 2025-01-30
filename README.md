# emi-indo-cordova-plugin-open-ai
 Cordova Plugin for using OpenAI's GPT apis. Supports GPT-3, ChatGPT, and GPT-4. ( Requires third party services https://openai.com/ that charge money )

### This plugin is not updated anymore, 
- New instead: https://github.com/EMI-INDO/cordova-plugin-deepseek-ai

> - [Code source:](https://platform.openai.com/docs/api-reference/introduction) - OpenAI:

## 💰Sponsor this project
  [![PayPal](https://img.shields.io/badge/PayPal-00457C?style=for-the-badge&logo=paypal&logoColor=white)](https://paypal.me/emiindo)  
  
  
  ## Features
- Completions ( model: text-davinci-003 )


 ## Coming soon Features
- chat completion ( model: gpt-3.5-turbo )  ( Finished )
- edit ( model: text-davinci-edit-001 )
- Images generations 
- Speech to text


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


# Completions

[Example ](https://github.com/EMI-INDO/emi-indo-cordova-plugin-open-ai/blob/main/example/index.html) - index.html:

```sh

let _Completion = () => {
    cordova.plugins.emiOpenAiPlugin.completions(  
	apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",  // apiKey https://platform.openai.com/
	timeOut = 0, //set this to 60, If error Failed to load response due to timeout
    prompt = "Hi? make me html code", // string
    max_tokens = 4000,
    temperature = 0,
	
    (result) => { alert(result) },
    (error) => { alert(error)

    
    });
}

// call _Completion();


```

## result
<img width="393" alt="2023-04-26_171947" src="https://user-images.githubusercontent.com/78555833/234546797-50c81ecc-9044-4316-8abf-a2f5afc2c7d0.png">



# chat

[Example ](https://github.com/EMI-INDO/emi-indo-cordova-plugin-open-ai/blob/main/example/index.html) - index.html:

```sh

let _chat = () => {
    cordova.plugins.emiOpenAiPlugin.chat(  
	apiKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",  // apiKey https://platform.openai.com/
	timeOut = 0, //set this to 60, If error Failed to load response due to timeout
    role = "user", // The role of the author of this message. One of system, user, or assistant.
    content = "Make me coffee",
	
    (result) => { alert(result) },
    (error) => { alert(error)

    
    });
}

// call _chat();


```


