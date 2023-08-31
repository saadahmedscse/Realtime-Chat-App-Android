### GENERAL

Introducing my innovative real-time chat app, seamlessly connecting users through instant messaging.
Developed for Android using Kotlin as the primary language and Java for select components, the app
employs Spring Boot on the backend to ensure smooth communication via WebSockets. This advanced
technology enables users to exchange messages in real-time, fostering dynamic and engaging
conversations. Experience the power of instant connectivity and responsive messaging with our
cutting-edge chat app.

### DESCRIPTION

```json
{
  "Min SDK": "24 Nougat"
  "Max SDK": "33 Tiramisu"
  "Primary Language": "Kotlin"
  "Partially Used": "Java"
  "HTTP": "Retrofit"
  "Pattern": "MVVM"
  "TCP": "WebSocket"
  "Backend": "Spring Boot"
  "Backend Project": "https://github.com/saadahmedscse/Realtime-Chat-App-Spring-Boot-WebSocket"
}
```

### USAGE

|                              Create Conversation                              |                        Realtime Messaging                        |
|:-----------------------------------------------------------------------------:|:----------------------------------------------------------------:|
| <img src="files/screenshots/Create%20Conversation.gif" width=272 height=auto> | <img src="files/screenshots/Messages.gif" width=552 height=auto> |

|                              Create Account                              |                              Login Account                              |
|:------------------------------------------------------------------------:|:-----------------------------------------------------------------------:|
| <img src="files/screenshots/Create%20Account.gif" width=272 height=auto> | <img src="files/screenshots/Login%20Account.gif" width=272 height=auto> |

### EXTERNAL PLUGINS & DEPENDENCIES

- Plugins

```
    id("kotlin-kapt")
```

- Dependencies

```
    //Popup Dialog
    implementation("com.saadahmedev.popup-dialog:popup-dialog:1.0.5")
    
    //Short Intent
    implementation("com.saadahmedev.shortintent:shortintent:3.0.0")
    
    //Tiny DB
    implementation("com.saadahmedev.tinydb:tinydb:1.0.0")
    
    //sSP sDP
    implementation("com.intuit.ssp:ssp-android:1.0.6")
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    
    //Circle Image View
    implementation("de.hdodenhof:circleimageview:3.1.0")
    
    //Picasso
    implementation("com.squareup.picasso:picasso:2.71828")
    
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    
    //OkHTTP
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    
    //Rounded Image View
    implementation("com.makeramen:roundedimageview:2.3.0")
    
    //WebSocket
    implementation("org.java-websocket:Java-WebSocket:1.5.1")
```
