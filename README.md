# AprendiendoTurco

## Español

### Sobre el proyecto

Proyecto de la asignatura Desarrollo de Aplicaciones Móviles (DSY1105), Duoc UC.

Integrantes: Rodrigo Callealta y Eduardo Bray.

Es un juego para personas que hablan turco y quieren aprender español. Aunque la carpeta del proyecto se llama AprendiendoTurco, la aplicación enseña español desde el turco. El contexto y las explicaciones están en turco y las alternativas en español. Las primeras pantallas tienen algunas etiquetas en ambos idiomas.

### Historia y funcionamiento

El jugador es Juan, está casado con Ana y tiene tres hijos. La familia acaba de mudarse y Juan trabaja doble turno. Antes de salir al trabajo, debe completar una conversación con su familia.

La historia tiene cinco preguntas con cuatro alternativas cada una:

1. Saludar al despertar.
2. Contar cómo durmió y proponer desayunar.
3. Pedir un café.
4. Ayudar a despertar a los niños.
5. Despedirse para ir al trabajo.

En cada pregunta hay:

- Una respuesta válida cercana o cariñosa.
- Una respuesta válida más formal.
- Una respuesta ruda en ese contexto que permite aprender una expresión chilena.
- Una respuesta incorrecta por confusión de vocabulario o de situación.

Las dos respuestas válidas permiten avanzar. La elección cambia el contexto de la siguiente pregunta; se pueden combinar respuestas cercanas y formales durante la misma partida. Las respuestas inadecuadas muestran su explicación y permiten reintentar.

### Qué incluye

- Inicio de sesión con dos cuentas de prueba y mensajes de error.
- Pantallas de bienvenida, contexto, preguntas y final.
- Traducciones al turco y explicaciones para las veinte alternativas.
- Animación de aparición y despliegue de la explicación con AnimatedVisibility.
- Cuatro sonidos según el tipo de respuesta y una marimba al entrar a la pantalla final.
- Botón para volver al inicio y comenzar otra partida.

Está hecha en Kotlin con Jetpack Compose y Material 3. Usa modelos y ViewModels para separar los datos, la lógica y las pantallas. El estado se maneja con StateFlow, la navegación con Navigation Compose y los sonidos con SoundPool.

### Cuentas de prueba

Puedes ingresar con cualquiera de estas dos cuentas:

- Correo: `ro.callealta@duocuc.cl`. Contraseña: `hola1234`.
- Correo: `ed.bray@duocuc.cl`. Contraseña: `hola1234`.

Las dos abren el mismo juego. Están escritas en el código para probar la aplicación; no hay registro de usuarios ni conexión a un servidor de autenticación.

### Cómo ejecutar la aplicación

1. Clona este repositorio o descarga y descomprime su código fuente.
2. En Android Studio, selecciona **Open** y abre la carpeta que contiene `settings.gradle.kts`, `build.gradle.kts` y `app`.
3. Espera a que termine la sincronización de Gradle. La primera configuración puede necesitar conexión a internet para descargar herramientas y dependencias.
4. Instala los componentes del SDK que Android Studio solicite. El proyecto está configurado con **compileSdk y targetSdk 37**, **minSdk 24** y un **JDK 25 para el proceso de Gradle**, indicado en `gradle/gradle-daemon-jvm.properties`.
5. Selecciona un emulador o un teléfono con **Android 7.0 (API 24) o superior**. Para un teléfono físico, habilita la depuración USB o vincúlalo mediante depuración inalámbrica.
6. Selecciona la configuración `app`, elige el dispositivo y pulsa Run.
7. Inicia sesión con una cuenta de prueba y pulsa **Comenzar**.

Para escuchar los efectos, comprueba el volumen multimedia del dispositivo y, si usas un emulador, la salida de audio del computador.

### Organización del código

El código principal está en `app/src/main/java/cl/duoc/aprendiendoturco/`:

- `model/`: datos del formulario, preguntas, alternativas y estado del quiz.
- `viewmodel/`: validación del inicio de sesión y manejo de la partida.
- `navigation/`: rutas y navegación entre pantallas.
- `ui/screens/`: pantallas con Jetpack Compose.
- `ui/theme/`: colores, tipografía y tema.
- `ui/utils/`: reproducción de efectos y utilidad de tamaño de ventana.

Los efectos están en `app/src/main/res/raw/` y las pruebas unitarias en `app/src/test/`.

### Pruebas unitarias

Verifican las cuentas de prueba, la validación del formulario, los caminos de la historia, los reintentos y el final de la quinta pregunta.

Desde la raíz del proyecto, en Windows:

```powershell
.\gradlew.bat testDebugUnitTest
```

En macOS o Linux:

```bash
./gradlew testDebugUnitTest
```

Las pruebas unitarias no sustituyen la comprobación visual ni la escucha de los efectos en un dispositivo.

### Alcance de esta versión

Es una versión de prueba. Las preguntas y cuentas están escritas en el código. El avance se mantiene en memoria y no se guarda después de cerrar el proceso de la aplicación. No graba la voz ni evalúa pronunciación.

La navegación utiliza actualmente la pantalla de bienvenida compacta para todos los tamaños. Los diseños específicos para tamaños medianos y grandes quedan pendientes.

## English

### About the project

Project for the Mobile Application Development course (DSY1105), Duoc UC.

Team members: Rodrigo Callealta and Eduardo Bray.

This is a game for Turkish speakers learning Spanish. Although the project folder is named AprendiendoTurco, the app teaches Spanish through Turkish. Story context and explanations are in Turkish, while answer choices are in Spanish. The initial screens include some bilingual labels.

### Story and gameplay

The player is Juan, who is married to Ana and has three children. The family has recently moved, and Juan works double shifts. Before leaving for work, he completes a conversation with his family.

The story contains five questions with four choices each:

1. Greeting someone after waking up.
2. Talking about sleep and suggesting breakfast.
3. Asking for coffee.
4. Helping wake up the children.
5. Saying goodbye before leaving for work.

Each question includes:

- A valid, warm or affectionate response.
- A valid, more formal response.
- A response that is rude in that situation and introduces a Chilean expression.
- An incorrect response based on a vocabulary or situational mix-up.

Both valid responses allow the player to continue. The chosen response changes the next question's context. Players can combine warm and formal responses within the same game. Inappropriate responses display an explanation and allow another attempt.

### Features

- Sign-in with two test accounts and error messages.
- Welcome, introduction, quiz and completion screens.
- Turkish translations and explanations for all twenty answer choices.
- A fade and vertical expansion animation for explanations using AnimatedVisibility.
- Four response-specific sounds and a marimba when entering the completion screen.
- A button to return home and start a new game.

The app is written in Kotlin with Jetpack Compose and Material 3. Models and ViewModels separate data, logic and screens. It uses StateFlow for state, Navigation Compose for navigation and SoundPool for sounds.

### Test accounts

Use either account:

- Email: `ro.callealta@duocuc.cl`. Password: `hola1234`.
- Email: `ed.bray@duocuc.cl`. Password: `hola1234`.

Both accounts access the same game. These are hardcoded demonstration credentials. There is no user registration or server-based authentication.

### Running the application

1. Clone this repository or download and extract its source code.
2. In Android Studio, choose **Open** and select the folder containing `settings.gradle.kts`, `build.gradle.kts` and `app`.
3. Wait for Gradle sync to finish. Initial setup may require internet access to download tools and dependencies.
4. Install any SDK components requested by Android Studio. The project uses **compileSdk and targetSdk 37**, **minSdk 24**, and **JDK 25 for the Gradle process**, as specified in `gradle/gradle-daemon-jvm.properties`.
5. Select an emulator or a phone running **Android 7.0 (API 24) or later**. For a physical phone, enable USB debugging or pair it using wireless debugging.
6. Select the `app` run configuration, choose the device and press Run.
7. Sign in with a test account and press **Comenzar / Başla**.

To hear the effects, check the device's media volume and, when using an emulator, the computer's audio output.

### Code organization

Main source code is located in `app/src/main/java/cl/duoc/aprendiendoturco/`:

- `model/`: form data, questions, answer choices and quiz state.
- `viewmodel/`: sign-in validation and quiz logic.
- `navigation/`: routes and navigation.
- `ui/screens/`: Jetpack Compose screens.
- `ui/theme/`: colors, typography and theme.
- `ui/utils/`: sound playback and window-size utility.

Sound effects are in `app/src/main/res/raw/`. Unit tests are in `app/src/test/`.

### Unit tests

Tests cover test-account validation, story branches, retries and completion of the fifth question.

From the project root on Windows:

```powershell
.\gradlew.bat testDebugUnitTest
```

On macOS or Linux:

```bash
./gradlew testDebugUnitTest
```

Unit tests do not replace visual checks or listening to the effects on a device.

### Current scope

This is a trial version. Questions and accounts are defined in code. Progress is kept in memory and is not saved after the application process closes. The app does not record voice or assess pronunciation.

Navigation currently uses the compact welcome screen at every window size. Dedicated medium and expanded layouts remain pending.
