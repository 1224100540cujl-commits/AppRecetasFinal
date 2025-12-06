# RecetApp 👨‍🍳

![RecetApp Banner](docs/screenshots/banner.png)

**RecetApp** es una aplicación Android moderna para gestionar tus recetas favoritas de forma intuitiva y organizada. Guarda, busca, califica y comparte recetas con una interfaz elegante y funcional.

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-purple.svg)](https://kotlinlang.org)
[![Android](https://img.shields.io/badge/Android-API%2024+-green.svg)](https://android.com)
[![Room](https://img.shields.io/badge/Room-2.6.0-blue.svg)](https://developer.android.com/jetpack/androidx/releases/room)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Capturas de Pantalla](#-capturas-de-pantalla)
- [Arquitectura](#-arquitectura)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Requisitos](#-requisitos)
- [Instalación](#-instalación)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Uso](#-uso)
- [Base de Datos](#-base-de-datos)
- [Contribuir](#-contribuir)
- [Licencia](#-licencia)
- [Contacto](#-contacto)

---

## ✨ Características

### Gestión de Recetas
- ✅ **Crear, editar y eliminar recetas** con información detallada
- 🖼️ **Emojis personalizables** para identificar cada receta
- 📝 **Ingredientes y pasos de preparación** organizados
- ⏱️ **Tiempo de preparación, porciones y dificultad**
- 🏷️ **Categorías** (Mexicana, Italiana, Japonesa, Americana, Ensaladas, Postres, Sopas, Bebidas)

### Organización Avanzada
- ⭐ **Sistema de favoritos** para acceso rápido a tus recetas preferidas
- 📂 **Colecciones personalizadas** para agrupar recetas por temas
- 🏷️ **Tags/Etiquetas** con colores personalizables para clasificación flexible
- 🔍 **Búsqueda inteligente** por nombre de receta o ingredientes
- 📸 **Galería de fotos** para cada receta con soporte de URLs

### Interacción Social
- ⭐ **Sistema de calificaciones** con estrellas (1-5) y comentarios
- 📊 **Promedio de valoraciones** visible en cada receta
- 👥 **Reseñas de usuarios** con nombre, fecha y comentarios
- 💬 **Historial de reseñas** ordenadas cronológicamente

### Funcionalidades Adicionales
- 🛒 **Lista de compras interactiva** con checkboxes para marcar ingredientes
- 📤 **Compartir listas de compras** por cualquier aplicación
- 🔔 **Notificaciones y recordatorios** personalizados para recetas
- 👤 **Perfil de usuario** con estadísticas (total recetas, favoritos, categorías)
- 🌙 **Interfaz moderna** con Material Design 3
- 💾 **Almacenamiento local** con Room Database (offline-first)
- 🎨 **Temas personalizables** con paleta de colores naranja

---

## 📱 Capturas de Pantalla

### Pantallas Principales

| Splash Screen | Login | Home | Detalle Receta |
|---------------|-------|------|----------------|
| ![Splash](docs/screenshots/splash.png) | ![Login](docs/screenshots/login.png) | ![Home](docs/screenshots/home.png) | ![Detail](docs/screenshots/detail.png) |

### Funcionalidades

| Búsqueda | Favoritos | Agregar Receta | Perfil |
|----------|-----------|----------------|--------|
| ![Search](docs/screenshots/search.png) | ![Favorites](docs/screenshots/favorites.png) | ![Add](docs/screenshots/add_recipe.png) | ![Profile](docs/screenshots/profile.png) |

### Características Avanzadas

| Colecciones | Tags | Lista de Compras | Notificaciones |
|-------------|------|------------------|----------------|
| ![Collections](docs/screenshots/collections.png) | ![Tags](docs/screenshots/tags.png) | ![Shopping](docs/screenshots/shopping_list.png) | ![Notifications](docs/screenshots/notifications.png) |



---

## 🏗️ Arquitectura

RecetApp sigue el patrón de arquitectura **MVVM (Model-View-ViewModel)** recomendado por Google para aplicaciones Android:
```
┌─────────────────────────────────────────────────────────┐
│                         UI Layer                         │
│  (Activities, Fragments, Adapters, XML Layouts)         │
└─────────────────┬───────────────────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────────────────┐
│                    ViewModel Layer                       │
│  (RecipeViewModel, CollectionViewModel, etc.)           │
│  - Gestiona lógica de presentación                      │
│  - Sobrevive a cambios de configuración                 │
└─────────────────┬───────────────────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────────────────┐
│                   Repository Layer                       │
│  (RecipeRepository, CollectionRepository, etc.)         │
│  - Abstracción de fuentes de datos                      │
└─────────────────┬───────────────────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────────────────────┐
│                  Data Source Layer                       │
│     (Room Database - DAOs, Entities, Database)          │
│  - SQLite local con Room                                │
└─────────────────────────────────────────────────────────┘
```

### Componentes Principales

#### 1. **UI Layer** (`ui/`)
Contiene todas las Activities y Adapters:
- **Activities**: Pantallas de la aplicación (MainActivity, DetailActivity, etc.)
- **Adapters**: RecyclerView adapters para listas (RecipeAdapter, FavoriteAdapter, etc.)
- **ViewBinding**: Acceso seguro a vistas XML

#### 2. **ViewModel Layer** (`ui/*/ViewModel.kt`)
- Gestiona la lógica de presentación
- Mantiene el estado de la UI
- Sobrevive a cambios de configuración
- Ejemplo: `RecipeViewModel`, `CollectionViewModel`

#### 3. **Repository Layer** (`data/repository/`)
- Abstracción que proporciona datos a los ViewModels
- Encapsula la lógica de acceso a datos
- Permite cambiar fuentes de datos sin afectar UI

#### 4. **Data Source Layer** (`data/local/`)
- **Room Database**: Base de datos SQLite
- **DAOs**: Interfaces para operaciones CRUD
- **Entities**: Clases que representan tablas

---

## 🛠️ Tecnologías Utilizadas

### Core
- **Kotlin 2.0.21** - Lenguaje de programación principal
- **Android SDK 24+** - Compatible desde Android 7.0 (Nougat)
- **Material Design 3** - Componentes modernos de UI/UX
- **ViewBinding** - Binding seguro de vistas sin findViewById

### Jetpack Components
- **Room 2.6.0** - Base de datos local SQLite ORM
- **LiveData** - Observación de datos reactiva y lifecycle-aware
- **ViewModel** - Gestión de estado UI y lógica de presentación
- **Lifecycle 2.6.2** - Manejo consciente del ciclo de vida
- **KSP 2.0.21** - Kotlin Symbol Processing para anotaciones

### UI/UX Libraries
- **RecyclerView 1.3.2** - Listas eficientes y reciclables
- **CardView 1.0.0** - Tarjetas de Material Design
- **ViewPager2 1.0.0** - Navegación por pestañas con swipe
- **TabLayout** - Pestañas Material Design
- **ConstraintLayout 2.1.4** - Layouts flexibles y responsivos
- **Glide 4.16.0** - Carga asíncrona y caché de imágenes

### Build & Tools
- **Gradle 8.13** - Sistema de compilación
- **AGP 8.13.1** - Android Gradle Plugin
- **Kotlin Coroutines** - Programación asíncrona
- **SharedPreferences** - Almacenamiento de preferencias de usuario

---

## 📦 Requisitos

### Requisitos de Desarrollo
- **Android Studio** Hedgehog (2023.1.1) o superior
- **JDK 21** (Java Development Kit)
- **Gradle 8.13** o superior
- **SDK Tools**:
  - Build Tools 36.0.0
  - Platform Tools
  - Android SDK Platform 36

### Requisitos del Dispositivo/Emulador
- **API mínima**: 24 (Android 7.0 Nougat)
- **API objetivo**: 36 (Android 14)
- **Espacio**: ~50 MB libres
- **RAM**: Mínimo 2 GB recomendado
- **Permisos**: INTERNET (para futuras funciones de sincronización)

---

## 🚀 Instalación

### Opción 1: Clonar desde GitHub
```bash
# Clonar el repositorio
git clone https://github.com/1224100540cujl-commits/AppRecetasFinal.git

# Navegar al directorio del proyecto
cd RecetApp

# Abrir con Android Studio
# File > Open > Seleccionar carpeta RecetApp

# Esperar sincronización de Gradle
# Build > Make Project
```

### Opción 2: Descargar APK

1. Ve a la sección [Releases]
2. Descarga el archivo `RecetApp-v1.0.apk`
3. Transfiere el APK a tu dispositivo Android
4. Habilita instalación de fuentes desconocidas:
   - Configuración > Seguridad > Orígenes desconocidos
5. Instala el APK

### Opción 3: Compilar desde Código Fuente
```bash
# Clonar repositorio
git clone https://github.com/1224100540cujl-commits/AppRecetasFinal.git
cd RecetApp

# Compilar APK Debug
./gradlew assembleDebug

# El APK se generará en:
# app/build/outputs/apk/debug/app-debug.apk

# Compilar APK Release (requiere configuración de firma)
./gradlew assembleRelease
```

### Primera Ejecución

1. **Splash Screen** (3 segundos) - Pantalla de bienvenida con logo
2. **Registro/Login** - Crea tu cuenta con nombre, email y contraseña
3. **Recetas Precargadas** - La app incluye 6 recetas de ejemplo:
   - 🌮 Tacos al Pastor (Mexicana)
   - 🍝 Pasta Carbonara (Italiana)
   - 🍣 Sushi Rolls (Japonesa)
   - 🍔 Hamburguesa Casera (Americana)
   - 🥗 Ensalada César (Ensaladas)
   - 🍕 Pizza Margarita (Italiana)
4. **¡Listo!** - Explora la app y agrega tus propias recetas

---

## 📂 Estructura del Proyecto
```
RecetApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/recetas/app/
│   │   │   │   ├── adapters/                    # RecyclerView Adapters
│   │   │   │   │   ├── RecipeAdapter.kt         # Adapter para lista de recetas
│   │   │   │   │   ├── FavoriteAdapter.kt       # Adapter para favoritos
│   │   │   │   │   ├── RatingAdapter.kt         # Adapter para calificaciones
│   │   │   │   │   ├── RecipeDetailPagerAdapter.kt # ViewPager2 tabs
│   │   │   │   │   ├── NotificationAdapter.kt   # Adapter de notificaciones
│   │   │   │   │   ├── RecipeMediaAdapter.kt    # Adapter de galería
│   │   │   │   │   ├── TagAdapter.kt            # Adapter de tags
│   │   │   │   │   └── CollectionAdapter.kt     # Adapter de colecciones
│   │   │   │   │
│   │   │   │   ├── data/
│   │   │   │   │   ├── local/
│   │   │   │   │   │   ├── dao/                 # Data Access Objects
│   │   │   │   │   │   │   ├── RecipeDao.kt     # CRUD de recetas
│   │   │   │   │   │   │   ├── RatingDao.kt     # CRUD de calificaciones
│   │   │   │   │   │   │   ├── CollectionDao.kt # CRUD de colecciones
│   │   │   │   │   │   │   ├── TagDao.kt        # CRUD de tags
│   │   │   │   │   │   │   ├── NotificationDao.kt # CRUD de notificaciones
│   │   │   │   │   │   │   └── RecipeMediaDao.kt # CRUD de media
│   │   │   │   │   │   │
│   │   │   │   │   │   └── database/
│   │   │   │   │   │       ├── AppDatabase.kt   # Configuración de Room DB
│   │   │   │   │   │       └── Converters.kt    # Type converters para Room
│   │   │   │   │   │
│   │   │   │   │   ├── model/                   # Data Models (Entities)
│   │   │   │   │   │   ├── Recipe.kt            # Entity: Receta
│   │   │   │   │   │   ├── Rating.kt            # Entity: Calificación
│   │   │   │   │   │   ├── Tag.kt               # Entity: Etiqueta
│   │   │   │   │   │   ├── RecipeTag.kt         # Entity: Relación M-N
│   │   │   │   │   │   ├── RecipeCollection.kt  # Entity: Colección
│   │   │   │   │   │   ├── RecipeCollectionItem.kt # Entity: Item colección
│   │   │   │   │   │   ├── Notification.kt      # Entity: Notificación
│   │   │   │   │   │   └── RecipeMedia.kt       # Entity: Media de receta
│   │   │   │   │   │
│   │   │   │   │   └── repository/              # Data Repositories
│   │   │   │   │       ├── RecipeRepository.kt  # Repositorio de recetas
│   │   │   │   │       ├── RatingRepository.kt  # Repositorio de ratings
│   │   │   │   │       ├── CollectionRepository.kt
│   │   │   │   │       ├── TagRepository.kt
│   │   │   │   │       ├── NotificationRepository.kt
│   │   │   │   │       └── RecipeMediaRepository.kt
│   │   │   │   │
│   │   │   │   └── ui/                          # UI Layer
│   │   │   │       ├── add/                     # Agregar/Editar Recetas
│   │   │   │       │   ├── AddRecipeActivity.kt
│   │   │   │       │   └── EditRecipeActivity.kt
│   │   │   │       │
│   │   │   │       ├── auth/                    # Autenticación
│   │   │   │       │   ├── SplashActivity.kt    # Pantalla de bienvenida
│   │   │   │       │   ├── LoginActivity.kt     # Login con validación
│   │   │   │       │   └── RegisterActivity.kt  # Registro de usuario
│   │   │   │       │
│   │   │   │       ├── collections/             # Colecciones de Recetas
│   │   │   │       │   ├── CollectionsActivity.kt
│   │   │   │       │   ├── CollectionDetailActivity.kt
│   │   │   │       │   └── CollectionViewModel.kt
│   │   │   │       │
│   │   │   │       ├── detail/                  # Detalle de Receta
│   │   │   │       │   ├── DetailActivity.kt    # Vista detallada con tabs
│   │   │   │       │   └── RatingViewModel.kt   # VM para calificaciones
│   │   │   │       │
│   │   │   │       ├── favorites/               # Favoritos
│   │   │   │       │   └── FavoritesActivity.kt
│   │   │   │       │
│   │   │   │       ├── home/                    # Pantalla Principal
│   │   │   │       │   ├── MainActivity.kt      # Activity principal
│   │   │   │       │   ├── RecipeViewModel.kt   # ViewModel principal
│   │   │   │       │   └── CategoriesActivity.kt # Vista por categoría
│   │   │   │       │
│   │   │   │       ├── media/                   # Galería de Fotos
│   │   │   │       │   ├── RecipeMediaActivity.kt
│   │   │   │       │   └── RecipeMediaViewModel.kt
│   │   │   │       │
│   │   │   │       ├── notifications/           # Notificaciones
│   │   │   │       │   ├── NotificationsActivity.kt
│   │   │   │       │   └── NotificationViewModel.kt
│   │   │   │       │
│   │   │   │       ├── profile/                 # Perfil de Usuario
│   │   │   │       │   ├── ProfileActivity.kt   # Perfil con estadísticas
│   │   │   │       │   └── EditProfileActivity.kt
│   │   │   │       │
│   │   │   │       ├── search/                  # Búsqueda
│   │   │   │       │   └── SearchActivity.kt    # Búsqueda en tiempo real
│   │   │   │       │
│   │   │   │       ├── shopping/                # Lista de Compras
│   │   │   │       │   └── ShoppingListActivity.kt
│   │   │   │       │
│   │   │   │       └── tags/                    # Tags/Etiquetas
│   │   │   │           ├── TagsActivity.kt
│   │   │   │           └── TagViewModel.kt
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── drawable/                    # Iconos y recursos gráficos
│   │   │   │   │   ├── ic_*.xml                 # Iconos vectoriales
│   │   │   │   │   ├── bg_*.xml                 # Backgrounds
│   │   │   │   │   └── chip_*.xml               # Chips personalizados
│   │   │   │   │
│   │   │   │   ├── layout/                      # Archivos XML de layouts
│   │   │   │   │   ├── activity_*.xml           # Layouts de Activities
│   │   │   │   │   ├── item_*.xml               # Items de RecyclerView
│   │   │   │   │   └── tab_*.xml                # Tabs de ViewPager
│   │   │   │   │
│   │   │   │   ├── menu/                        # Menús
│   │   │   │   │   └── bottom_nav_menu.xml      # Bottom Navigation
│   │   │   │   │
│   │   │   │   ├── mipmap/                      # Launcher icons
│   │   │   │   │   └── ic_launcher*.png
│   │   │   │   │
│   │   │   │   └── values/                      # Valores y recursos
│   │   │   │       ├── colors.xml               # Paleta de colores
│   │   │   │       ├── strings.xml              # Textos en español
│   │   │   │       └── themes.xml               # Temas Material Design
│   │   │   │
│   │   │   └── AndroidManifest.xml              # Manifest con permisos
│   │   │
│   │   └── build.gradle.kts                     # Configuración del módulo
│   │
│   └── proguard-rules.pro                       # Reglas de ofuscación
│
├── docs/
│   └── screenshots/                             # Capturas de pantalla
│       ├── banner.png
│       ├── splash.png
│       ├── login.png
│       ├── home.png
│       ├── detail.png
│       ├── search.png
│       ├── favorites.png
│       ├── add_recipe.png
│       ├── profile.png
│       ├── collections.png
│       ├── tags.png
│       ├── shopping_list.png
│       └── notifications.png
│
├── gradle/
│   ├── libs.versions.toml                       # Catálogo de versiones
│   └── wrapper/
│       └── gradle-wrapper.properties
│
├── .gitignore                                   # Archivos ignorados por Git
├── build.gradle.kts                             # Build script raíz
├── settings.gradle.kts                          # Configuración de Gradle
├── gradlew                                      # Gradle wrapper (Linux/Mac)
├── gradlew.bat                                  # Gradle wrapper (Windows)
├── LICENSE                                      # Licencia MIT
└── README.md                                    # Este archivo
```

### Descripción de Directorios Principales

#### **`adapters/`**
Contiene todos los adaptadores de RecyclerView para mostrar listas:
- `RecipeAdapter`: Lista principal de recetas en grid
- `FavoriteAdapter`: Lista de recetas favoritas con opción de eliminar
- `RatingAdapter`: Lista de reseñas con estrellas y comentarios
- `RecipeDetailPagerAdapter`: Tabs de ingredientes e instrucciones

#### **`data/local/dao/`**
Interfaces DAO (Data Access Objects) con métodos de consulta Room:
- Operaciones CRUD (Create, Read, Update, Delete)
- Consultas personalizadas con SQL
- LiveData para observación reactiva

#### **`data/model/`**
Entidades de Room (tablas de base de datos):
- Anotaciones `@Entity`, `@PrimaryKey`, `@ColumnInfo`
- Relaciones uno-a-muchos y muchos-a-muchos
- Data classes de Kotlin

#### **`data/repository/`**
Capa de abstracción entre ViewModels y DAOs:
- Encapsula lógica de acceso a datos
- Permite testing más fácil
- Facilita cambios de fuentes de datos

#### **`ui/`**
Todas las Activities organizadas por funcionalidad:
- Cada módulo tiene su propio subdirectorio
- ViewModels en el mismo paquete que sus Activities
- Separación clara de responsabilidades

---

## 💡 Uso

### 1. Gestión de Recetas

#### Crear una Nueva Receta

1. Toca el botón **"+"** en la barra de navegación inferior
2. Completa los campos obligatorios:
✓ Nombre de la receta (ej: "Tacos al Pastor")
✓ Emoji representativo (toca el área de imagen para elegir)
✓ Tiempo de preparación (ej: "30 min")
✓ Número de porciones (ej: 4)
✓ Categoría (selecciona del dropdown)
✓ Dificultad (Fácil, Media, Difícil)
✓ Ingredientes (separados por comas)
✓ Pasos de preparación
3. Toca **"Guardar Receta"**
4. La receta aparecerá inmediatamente en el inicio

#### Editar una Receta Existente

1. Abre una receta desde el inicio o búsqueda
2. Toca el botón **"Editar"** en la pantalla de detalle
3. Modifica los campos necesarios
4. Toca **"Guardar Cambios"**
5. Para eliminar: toca el icono de papelera y confirma

#### Ver Detalle de Receta

La pantalla de detalle incluye:
- **Header**: Emoji grande, nombre y categoría
- **Info Cards**: Tiempo, porciones y dificultad
- **Tabs**:
  - **Ingredientes**: Lista con checkboxes
  - **Preparación**: Pasos numerados
- **Reseñas**: Promedio de estrellas y comentarios
- **Acciones**:
  - ❤️ Agregar a favoritos
  - ⭐ Calificar receta
  - 🏷️ Gestionar tags
  - 🔔 Crear recordatorio
  - 📸 Galería de fotos
  - 🛒 Lista de compras
  - ✏️ Editar receta

---

### 2. Búsqueda y Filtrado

#### Búsqueda por Texto

1. Toca **"Buscar"** en la navegación inferior
2. Escribe en el campo de búsqueda:
   - Busca por nombre: "Tacos"
   - Busca por ingrediente: "Pollo", "Tomate"
3. Los resultados se filtran en tiempo real
4. Toca cualquier receta para ver detalles

#### Búsqueda por Ingredientes

La app incluye chips predefinidos:
- 🍗 Pollo
- 🍝 Pasta
- 🍅 Tomate
- 🧀 Queso
- 🥑 Aguacate
- 🍚 Arroz

Toca cualquier chip para buscar recetas que contengan ese ingrediente.

#### Filtrar por Categoría

1. En la pantalla de inicio, desliza horizontalmente las categorías
2. Selecciona una categoría específica:
   - 🌮 Mexicana
   - 🍕 Italiana
   - 🍣 Japonesa
   - 🍔 Americana
   - 🥗 Ensaladas
   - 🍰 Postres
   - 🍲 Sopas
   - 🥤 Bebidas
3. Se abre una vista con todas las recetas de esa categoría

---

### 3. Sistema de Favoritos

#### Agregar a Favoritos

1. Abre una receta
2. Toca el icono de corazón ❤️ en la toolbar
3. El corazón se vuelve rojo = agregado a favoritos

#### Ver Favoritos

1. Toca **"Favoritos"** en la navegación inferior
2. Verás todas tus recetas favoritas en una lista
3. Cada item muestra:
   - Emoji de la receta
   - Nombre
   - Categoría
   - Tiempo de preparación
   - Número de porciones
   - Botón para eliminar de favoritos

#### Eliminar de Favoritos

**Opción 1**: Desde la lista de favoritos
- Toca el icono de corazón rojo en el item

**Opción 2**: Desde el detalle
- Toca el corazón rojo en la toolbar

---

### 4. Colecciones

Las colecciones te permiten agrupar recetas por temas personalizados.

#### Crear una Colección

1. Ve a **Perfil > Mis Colecciones**
2. Toca el botón **"+"** flotante
3. Ingresa:
   - Nombre (ej: "Postres para Navidad")
   - Descripción opcional
4. Toca **"Crear"**

#### Agregar Recetas a una Colección

1. Abre la colección
2. Toca el botón **"+"** flotante
3. Selecciona recetas de tu lista
4. Confirma para agregar

#### Gestionar Colecciones

- **Ver recetas**: Toca una colección para ver sus recetas
- **Eliminar receta**: Toca el icono de eliminar en cada receta
- **Eliminar colección**: Toca el icono de papelera en la lista

---

### 5. Sistema de Tags/Etiquetas

Los tags permiten clasificación cruzada de recetas.

#### Crear Tags

1. Ve a **Perfil > Mis Tags**
2. Toca el botón **"+"** flotante
3. Escribe el nombre del tag (ej: "Vegetariano", "Bajo en Calorías")
4. El color se asigna automáticamente
5. Toca **"Crear"**

#### Asignar Tags a Recetas

1. Abre una receta
2. Toca **"🏷️ Gestionar Tags"**
3. Marca/desmarca los tags deseados
4. Los tags aparecen inmediatamente debajo del nombre

#### Ver Recetas por Tag

1. Ve a **Perfil > Mis Tags**
2. Cada tag muestra el número de recetas asociadas
3. Toca un tag para ver todas sus recetas (próximamente)

---

### 6. Calificaciones y Reseñas

#### Calificar una Receta

1. Abre una receta
2. Toca "⭐ Calificar Receta"
3. Selecciona estrellas (1-5)
4. Escribe un comentario opcional
5. Toca "Enviar"
Ver Reseñas

Las reseñas aparecen en la parte inferior del detalle
Cada reseña muestra:

Avatar del usuario
Nombre
Calificación con estrellas
Comentario
Tiempo transcurrido ("Hace 2 días")



Promedio de Calificaciones

Se muestra en un card destacado
Incluye:

Número grande con el promedio
Barra de estrellas visual
Conteo total de reseñas




7. Lista de Compras
Generar Lista de Compras

Abre una receta
Toca "🛒 Lista de Compras"
Se abre automáticamente con todos los ingredientes

Usar la Lista

Marcar ingredientes: Toca el checkbox al comprar
Limpiar lista: Toca "Limpiar Lista" para desmarcar todo
Compartir: Toca "Compartir Lista" para enviar por:

WhatsApp
Email
Mensajes
Cualquier app de compartir



Formato de lista compartida:
🛒 Lista de Compras
Receta: Tacos al Pastor

Ingredientes:
⬜ Carne de cerdo
⬜ Piña
✅ Tortillas (marcado)
⬜ Cilantro

8. Notificaciones y Recordatorios
Crear Recordatorio para Receta

Abre una receta
Toca "🔔 Crear Recordatorio"
Configura:

Mensaje personalizado
Fecha (selector de calendario)
Hora (selector de hora)


Toca "Crear"

Ver Notificaciones

Ve a Perfil > Mis Notificaciones
Verás todas las notificaciones:

No leídas: Fondo naranja
Leídas: Fondo blanco


Toca una notificación para marcarla como leída

Gestionar Notificaciones

Eliminar individual: Toca el icono de papelera
Limpiar leídas: Toca "Limpiar leídas" en la parte superior


9. Galería de Fotos
Agregar Fotos a una Receta

Abre una receta
Toca "📸 Galería de Fotos"
Toca el botón "+" flotante
Ingresa:

URL de la imagen
Tipo (📸 Foto o 🎥 Video)


Toca "Agregar"

Nota: Actualmente solo soporta URLs públicas de imágenes.
Ver Galería

Las fotos se muestran en tarjetas
Toca una foto para verla en grande
Cada foto muestra:

Imagen en miniatura
Tipo (PHOTO/VIDEO)
URL
Botón para eliminar




10. Perfil de Usuario
Estadísticas
Tu perfil muestra automáticamente:

📊 Total de Recetas creadas
❤️ Total de Favoritos
🏷️ Categorías Únicas utilizadas

Editar Perfil

Ve a Perfil > Editar Perfil
Modifica:

Nombre
Avatar (emoji)
Contraseña (opcional)


El correo no se puede modificar
Toca "Guardar Cambios"

Opciones del Perfil
Desde el perfil puedes acceder a:

✏️ Editar Perfil
🏠 Mis Recetas
❤️ Mis Favoritos
🛒 Listas de Compras
📂 Mis Colecciones
🔔 Mis Notificaciones
🏷️ Mis Tags
🚪 Cerrar Sesión


🗄️ Base de Datos
RecetApp utiliza Room Database, una capa de abstracción sobre SQLite que proporciona:

Verificación de consultas en tiempo de compilación
Menos código boilerplate
Integración con LiveData y Coroutines

Esquema de Base de Datos
La aplicación utiliza 8 tablas principales:
1. recipes - Recetas
kotlin@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,              // Nombre de la receta
    val category: String,          // Categoría (Mexicana, Italiana, etc.)
    val time: String,              // Tiempo de preparación
    val servings: Int,             // Número de porciones
    val difficulty: String,        // Fácil, Media, Difícil
    val ingredients: String,       // Ingredientes separados por comas
    val instructions: String,      // Pasos de preparación
    val imageUrl: String?,         // URL o emoji
    val isFavorite: Boolean = false // Estado de favorito
)
Consultas principales:

getAllRecipes(): Todas las recetas ordenadas por nombre
getRecipeById(id): Receta específica
getFavorites(): Solo recetas favoritas
searchRecipes(query): Búsqueda por nombre
getRecipesByCategory(category): Filtrar por categoría

2. ratings - Calificaciones
kotlin@Entity(tableName = "ratings")
data class Rating(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipeId: Int,             // FK a recipes
    val userName: String,          // Nombre del usuario
    val stars: Int,                // 1-5 estrellas
    val comment: String,           // Comentario opcional
    val createdAt: Long            // Timestamp de creación
)
Relación:

Muchos-a-Uno con recipes (una receta tiene muchas calificaciones)

Consultas:

getRatingsByRecipe(recipeId): Todas las calificaciones de una receta
getAverageRating(recipeId): Promedio de estrellas
getRatingsCount(recipeId): Total de calificaciones

3. recipe_collections - Colecciones
kotlin@Entity(tableName = "recipe_collections")
data class RecipeCollection(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,              // Nombre de la colección
    val description: String,       // Descripción opcional
    val createdAt: Long            // Timestamp de creación
)
4. recipe_collection_items - Items de Colección
kotlin@Entity(tableName = "recipe_collection_items")
data class RecipeCollectionItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val collectionId: Int,         // FK a recipe_collections
    val recipeId: Int,             // FK a recipes
    val addedAt: Long              // Timestamp de agregado
)
Relación:

Tabla intermedia para relación Muchos-a-Muchos entre colecciones y recetas

5. tags - Etiquetas
kotlin@Entity(tableName = "tags")
data class Tag(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,              // Nombre del tag
    val color: String = "#FF6B35"  // Color en formato HEX
)
6. recipe_tags - Relación Recetas-Tags
kotlin@Entity(
    tableName = "recipe_tags",
    primaryKeys = ["recipeId", "tagId"]
)
data class RecipeTag(
    val recipeId: Int,             // FK a recipes
    val tagId: Int                 // FK a tags
)
Relación:

Muchos-a-Muchos entre recetas y tags

Consultas:

getTagsForRecipe(recipeId): Todos los tags de una receta
isRecipeTagged(recipeId, tagId): Verificar si tiene tag
getRecipeCountForTag(tagId): Contar recetas con ese tag

7. notifications - Notificaciones
kotlin@Entity(tableName = "notifications")
data class Notification(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,             // Título de la notificación
    val message: String,           // Mensaje
    val triggerTime: Date,         // Fecha/hora programada
    val type: String,              // Tipo (REMINDER, etc.)
    var isRead: Boolean,           // Estado de lectura
    val createdAt: Date            // Fecha de creación
)
Consultas:

getAllNotifications(): Todas ordenadas por fecha
getUnreadNotifications(): Solo no leídas
getUnreadCount(): Contar no leídas
markAsRead(id): Marcar como leída

8. recipe_media - Galería de Fotos
kotlin@Entity(tableName = "recipe_media")
data class RecipeMedia(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val recipeId: Int,             // FK a recipes
    val url: String,               // URL de la imagen/video
    val type: String,              // PHOTO o VIDEO
    val order: Int,                // Orden de visualización
    val createdAt: Long            // Timestamp de creación
)
```

**Relación:**
- Uno-a-Muchos con `recipes` (una receta tiene muchas fotos)

---

### Diagrama de Relaciones
```
┌─────────────┐
│   recipes   │◄──────┐
└──────┬──────┘       │
       │              │
       │ 1:N          │ N:M
       │              │
┌──────▼──────┐  ┌────┴─────────┐
│   ratings   │  │ recipe_tags  │
└─────────────┘  └────┬─────────┘
                      │
                      │ N:M
                      │
                 ┌────▼──┐
                 │ tags  │
                 └───────┘

┌─────────────┐
│   recipes   │◄──────────────┐
└──────┬──────┘               │
       │                      │ N:M
       │ 1:N                  │
       │                 ┌────┴──────────────────┐
┌──────▼──────────┐     │ recipe_collection_    │
│ recipe_media    │     │ items                 │
└─────────────────┘     └────┬──────────────────┘
                             │
                             │ N:1
                             │
                    ┌────────▼────────────┐
                    │ recipe_collections  │
                    └─────────────────────┘

Migraciones
El proyecto está configurado con:
kotlin.fallbackToDestructiveMigration()
Esto significa que cualquier cambio en el esquema eliminará y recreará la base de datos. En producción, se deberían implementar migraciones adecuadas:
kotlinval MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE recipes ADD COLUMN new_column TEXT")
    }
}

Type Converters
Para manejar tipos personalizados en Room:
kotlinclass Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
Esto permite almacenar objetos Date como Long en SQLite.

Codigo con comentarios KDoc
### 1. Entity - Modelo de Receta
```kotlin
package com.recetas.app.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidad que representa una receta en la base de datos Room.
 * 
 * Esta clase define el esquema de la tabla "recipes" en SQLite,
 * almacenando toda la información necesaria de una receta.
 *
 * @property id Identificador único autogenerado para cada receta
 * @property name Nombre descriptivo de la receta (ej: "Tacos al Pastor")
 * @property category Categoría culinaria (Mexicana, Italiana, Japonesa, etc.)
 * @property time Tiempo estimado de preparación en formato texto (ej: "30 min")
 * @property servings Número de porciones que produce la receta
 * @property difficulty Nivel de dificultad: "Fácil", "Media" o "Difícil"
 * @property ingredients Lista de ingredientes separados por comas
 * @property instructions Pasos de preparación en formato texto
 * @property imageUrl URL de imagen o emoji representativo de la receta
 * @property isFavorite Indica si el usuario marcó esta receta como favorita
 *
 * @author Cristian y David
 * @since 1.0
 */
@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "time")
    val time: String,

    @ColumnInfo(name = "servings")
    val servings: Int,

    @ColumnInfo(name = "difficulty")
    val difficulty: String,

    @ColumnInfo(name = "ingredients")
    val ingredients: String,

    @ColumnInfo(name = "instructions")
    val instructions: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null,

    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean = false
)
```

---

### 2. DAO - Interfaz de Acceso a Datos
```kotlin
package com.recetas.app.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.recetas.app.data.model.Recipe

/**
 * Data Access Object para operaciones CRUD de recetas.
 * 
 * Room genera automáticamente la implementación de esta interfaz,
 * proporcionando acceso seguro y eficiente a la base de datos.
 * Todas las operaciones suspend se ejecutan en un hilo de fondo.
 *
 * @author Cristian y David
 * @since 1.0
 */
@Dao
interface RecipeDao {
    
    /**
     * Inserta una nueva receta en la base de datos.
     * 
     * @param recipe La receta a insertar
     * @throws SQLiteConstraintException si hay conflicto de claves
     */
    @Insert
    suspend fun insert(recipe: Recipe)

    /**
     * Actualiza una receta existente en la base de datos.
     * 
     * @param recipe La receta con los datos actualizados
     * @return Número de filas afectadas (0 si no existe)
     */
    @Update
    suspend fun update(recipe: Recipe)

    /**
     * Elimina una receta de la base de datos.
     * 
     * @param recipe La receta a eliminar
     */
    @Delete
    suspend fun delete(recipe: Recipe)

    /**
     * Obtiene todas las recetas ordenadas alfabéticamente.
     * 
     * @return LiveData que emite la lista de recetas automáticamente
     * cuando hay cambios en la base de datos
     */
    @Query("SELECT * FROM recipes ORDER BY name ASC")
    fun getAllRecipes(): LiveData<List>

    /**
     * Busca una receta específica por su ID.
     * 
     * @param recipeId El identificador único de la receta
     * @return LiveData con la receta encontrada o null
     */
    @Query("SELECT * FROM recipes WHERE id = :recipeId")
    fun getRecipeById(recipeId: Int): LiveData

    /**
     * Obtiene todas las recetas marcadas como favoritas.
     * 
     * @return LiveData con la lista de recetas favoritas
     */
    @Query("SELECT * FROM recipes WHERE is_favorite = 1")
    fun getFavorites(): LiveData<List>

    /**
     * Busca recetas que coincidan con el término de búsqueda.
     * 
     * La búsqueda es case-insensitive y busca coincidencias parciales
     * en el nombre de la receta.
     * 
     * @param searchQuery Término de búsqueda (se añaden wildcards automáticamente)
     * @return LiveData con las recetas que coinciden
     */
    @Query("SELECT * FROM recipes WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchRecipes(searchQuery: String): LiveData<List>

    /**
     * Filtra recetas por categoría específica.
     * 
     * @param category Nombre de la categoría (Mexicana, Italiana, etc.)
     * @return LiveData con las recetas de esa categoría
     */
    @Query("SELECT * FROM recipes WHERE category = :category")
    fun getRecipesByCategory(category: String): LiveData<List>
}
```

---

### 3. Repository - Capa de Abstracción
```kotlin
package com.recetas.app.data.repository

import androidx.lifecycle.LiveData
import com.recetas.app.data.local.dao.RecipeDao
import com.recetas.app.data.model.Recipe

/**
 * Repositorio que maneja el acceso a datos de recetas.
 * 
 * Actúa como capa intermedia entre el ViewModel y la fuente de datos,
 * permitiendo cambiar la implementación sin afectar la capa de presentación.
 * Encapsula la lógica de acceso a datos y proporciona una API limpia.
 *
 * @property recipeDao DAO de Room para operaciones en la base de datos
 * @constructor Crea un repositorio con el DAO especificado
 * 
 * @author Cristian y David
 * @since 1.0
 */
class RecipeRepository(private val recipeDao: RecipeDao) {

    /**
     * LiveData con todas las recetas disponibles.
     * Se actualiza automáticamente cuando hay cambios en la BD.
     */
    val allRecipes: LiveData<List> = recipeDao.getAllRecipes()
    
    /**
     * LiveData con las recetas marcadas como favoritas.
     * Se actualiza automáticamente cuando cambia el estado de favoritos.
     */
    val favorites: LiveData<List> = recipeDao.getFavorites()

    /**
     * Inserta una nueva receta en la base de datos de forma asíncrona.
     * 
     * Esta función suspendida debe ser llamada desde una coroutine o
     * desde otra función suspendida. Room ejecuta la operación en un
     * hilo de fondo automáticamente.
     * 
     * @param recipe La receta a insertar
     */
    suspend fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    /**
     * Actualiza los datos de una receta existente.
     * 
     * @param recipe La receta con los datos actualizados
     */
    suspend fun update(recipe: Recipe) {
        recipeDao.update(recipe)
    }

    /**
     * Elimina una receta de la base de datos.
     * 
     * @param recipe La receta a eliminar
     */
    suspend fun delete(recipe: Recipe) {
        recipeDao.delete(recipe)
    }

    /**
     * Realiza una búsqueda de recetas por nombre.
     * 
     * @param query Término de búsqueda
     * @return LiveData con los resultados de la búsqueda
     */
    fun searchRecipes(query: String): LiveData<List> {
        return recipeDao.searchRecipes(query)
    }

    /**
     * Obtiene recetas filtradas por categoría.
     * 
     * @param category Nombre de la categoría a filtrar
     * @return LiveData con las recetas de la categoría especificada
     */
    fun getRecipesByCategory(category: String): LiveData<List> {
        return recipeDao.getRecipesByCategory(category)
    }

    /**
     * Obtiene una receta específica por su ID.
     * 
     * @param id Identificador único de la receta
     * @return LiveData con la receta solicitada
     */
    fun getRecipeById(id: Int): LiveData {
        return recipeDao.getRecipeById(id)
    }
}
```

---

### 4. ViewModel - Lógica de Presentación
```kotlin
package com.recetas.app.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.recetas.app.data.local.database.AppDatabase
import com.recetas.app.data.model.Recipe
import com.recetas.app.data.repository.RecipeRepository
import kotlinx.coroutines.launch

/**
 * ViewModel principal para la gestión de recetas en la UI.
 * 
 * Mantiene los datos de la UI y sobrevive a cambios de configuración
 * (como rotaciones de pantalla). Proporciona métodos para realizar
 * operaciones CRUD y expone LiveData para observación reactiva.
 * 
 * Extiende AndroidViewModel para tener acceso al Application context,
 * necesario para inicializar la base de datos Room.
 *
 * @param application Contexto de la aplicación
 * 
 * @author Cristian y David
 * @since 1.0
 */
class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * Repositorio que maneja el acceso a datos.
     * Privado para encapsular la implementación.
     */
    private val repository: RecipeRepository
    
    /**
     * LiveData con todas las recetas disponibles.
     * La UI puede observar este LiveData para recibir actualizaciones automáticas.
     */
    val allRecipes: LiveData<List>
    
    /**
     * LiveData con las recetas marcadas como favoritas.
     * Se actualiza automáticamente cuando cambia el estado de favoritos.
     */
    val favorites: LiveData<List>

    /**
     * Bloque de inicialización que se ejecuta al crear el ViewModel.
     * 
     * Inicializa la base de datos Room, crea el repositorio y
     * obtiene las referencias a LiveData para exponer a la UI.
     */
    init {
        // Obtener la instancia singleton de la base de datos
        val recipeDao = AppDatabase.getDatabase(application).recipeDao()
        
        // Crear el repositorio con el DAO
        repository = RecipeRepository(recipeDao)
        
        // Obtener LiveData del repositorio
        allRecipes = repository.allRecipes
        favorites = repository.favorites
    }

    /**
     * Inserta una nueva receta en la base de datos.
     * 
     * Lanza una coroutine en el viewModelScope para ejecutar la operación
     * de forma asíncrona. El scope se cancela automáticamente cuando
     * el ViewModel es destruido.
     * 
     * @param recipe La receta a insertar
     */
    fun insert(recipe: Recipe) = viewModelScope.launch {
        repository.insert(recipe)
    }

    /**
     * Actualiza una receta existente.
     * 
     * Útil para modificar datos o cambiar el estado de favorito.
     * 
     * @param recipe La receta con los datos actualizados
     */
    fun update(recipe: Recipe) = viewModelScope.launch {
        repository.update(recipe)
    }

    /**
     * Elimina una receta de la base de datos.
     * 
     * @param recipe La receta a eliminar
     */
    fun delete(recipe: Recipe) = viewModelScope.launch {
        repository.delete(recipe)
    }

    /**
     * Busca recetas que coincidan con el término de búsqueda.
     * 
     * @param query Término de búsqueda (nombre o ingrediente)
     * @return LiveData con los resultados filtrados
     */
    fun searchRecipes(query: String): LiveData<List> {
        return repository.searchRecipes(query)
    }

    /**
     * Obtiene recetas de una categoría específica.
     * 
     * @param category Nombre de la categoría (Mexicana, Italiana, etc.)
     * @return LiveData con las recetas filtradas por categoría
     */
    fun getRecipesByCategory(category: String): LiveData<List> {
        return repository.getRecipesByCategory(category)
    }

    /**
     * Obtiene una receta específica por su ID.
     * 
     * @param id Identificador único de la receta
     * @return LiveData con la receta solicitada
     */
    fun getRecipeById(id: Int): LiveData {
        return repository.getRecipeById(id)
    }
}
```

---

### 5. MainActivity - Activity Principal
```kotlin
package com.recetas.app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.recetas.app.R
import com.recetas.app.adapters.RecipeAdapter
import com.recetas.app.data.model.Recipe
import com.recetas.app.databinding.ActivityMainBinding
import com.recetas.app.ui.add.AddRecipeActivity
import com.recetas.app.ui.detail.DetailActivity
import com.recetas.app.ui.favorites.FavoritesActivity
import com.recetas.app.ui.profile.ProfileActivity
import com.recetas.app.ui.search.SearchActivity

/**
 * Activity principal de la aplicación RecetApp.
 * 
 * Muestra el listado de recetas en un RecyclerView con diseño de grid,
 * permite filtrar por categorías y navegar a otras pantallas mediante
 * el Bottom Navigation. Es el punto de entrada después del login.
 * 
 * Utiliza ViewBinding para acceso seguro a las vistas y ViewModel
 * para manejar los datos con arquitectura MVVM.
 *
 * @author Cristian y David
 * @since 1.0
 */
class MainActivity : AppCompatActivity() {

    /**
     * ViewBinding generado automáticamente para acceso seguro a vistas.
     * Evita el uso de findViewById y proporciona referencias tipadas.
     */
    private lateinit var binding: ActivityMainBinding
    
    /**
     * ViewModel que maneja la lógica de datos de recetas.
     * Sobrevive a cambios de configuración como rotaciones.
     */
    private lateinit var recipeViewModel: RecipeViewModel
    
    /**
     * Adapter para el RecyclerView que muestra las recetas.
     * Maneja la conversión de datos a vistas.
     */
    private lateinit var adapter: RecipeAdapter

    /**
     * Lista de categorías disponibles para filtrar recetas.
     * "Todas" muestra todas las recetas sin filtrar.
     */
    private val categories = listOf(
        "Todas", "Mexicana", "Italiana", "Japonesa", 
        "Americana", "Ensaladas", "Postres"
    )

    /**
     * Método del ciclo de vida llamado cuando se crea la Activity.
     * 
     * Inicializa el ViewBinding, configura el ViewModel, prepara el
     * RecyclerView y establece los observadores para datos reactivos.
     * 
     * @param savedInstanceState Estado guardado de la instancia anterior
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inflar el layout usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar ViewModel con ViewModelProvider
        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        // Configurar RecyclerView con adapter y layout manager
        setupRecyclerView()

        // Configurar chips de categorías para filtrado
        setupCategories()

        // Observar cambios en la lista de recetas
        observeRecipes()

        // Configurar listeners de navegación
        setupClickListeners()

        // Configurar Bottom Navigation
        setupBottomNavigation()

        // Insertar recetas de ejemplo si la BD está vacía
        insertSampleRecipes()
    }

    /**
     * Configura el RecyclerView con GridLayoutManager y el adapter.
     * 
     * El RecyclerView muestra las recetas en una cuadrícula de 2 columnas.
     * El adapter maneja el click en cada receta para navegar al detalle.
     */
    private fun setupRecyclerView() {
        adapter = RecipeAdapter { recipe ->
            // Lambda que se ejecuta al hacer click en una receta
            navigateToDetail(recipe)
        }

        // Configurar RecyclerView con grid de 2 columnas
        binding.recipesRecyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recipesRecyclerView.adapter = adapter
    }

    /**
     * Observa los cambios en el LiveData de recetas del ViewModel.
     * 
     * Cuando hay cambios en la base de datos, este observer recibe
     * la lista actualizada y la pasa al adapter para mostrarla.
     */
    private fun observeRecipes() {
        recipeViewModel.allRecipes.observe(this) { recipes ->
            // Actualizar el adapter con la nueva lista
            adapter.setRecipes(recipes)
        }
    }

    /**
     * Navega a la pantalla de detalle de una receta específica.
     * 
     * @param recipe La receta seleccionada por el usuario
     */
    private fun navigateToDetail(recipe: Recipe) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("RECIPE_ID", recipe.id)
        }
        startActivity(intent)
    }

    /**
     * Configura los chips de categorías en un scroll horizontal.
     * 
     * Crea dinámicamente un chip por cada categoría y configura
     * el listener para filtrar recetas al seleccionar una categoría.
     */
    private fun setupCategories() {
        categories.forEach { category ->
            // Inflar el layout del chip desde XML
            val chip = LayoutInflater.from(this)
                .inflate(R.layout.item_category_chip, binding.categoriesChipGroup, false) as Chip

            chip.text = category
            chip.isChecked = category == "Todas"

            // Configurar listener para filtrado
            chip.setOnClickListener {
                filterByCategory(category)
            }

            // Agregar el chip al ChipGroup
            binding.categoriesChipGroup.addView(chip)
        }
    }

    /**
     * Filtra las recetas por categoría seleccionada.
     * 
     * Si la categoría es "Todas", muestra todas las recetas.
     * De lo contrario, navega a CategoriesActivity con el filtro.
     * 
     * @param category Nombre de la categoría seleccionada
     */
    private fun filterByCategory(category: String) {
        if (category == "Todas") {
            // Mostrar todas las recetas
            recipeViewModel.allRecipes.observe(this) { recipes ->
                adapter.setRecipes(recipes)
            }
        } else {
            // Navegar a vista filtrada por categoría
            val intent = Intent(this, CategoriesActivity::class.java).apply {
                putExtra("CATEGORY", category)
            }
            startActivity(intent)
        }
    }

    /**
     * Configura los listeners para elementos de la UI.
     * 
     * Incluye el campo de búsqueda y el botón de perfil en el header.
     */
    private fun setupClickListeners() {
        // Click en barra de búsqueda
        binding.searchEditText.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        // Click en botón de perfil
        binding.profileButton.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    /**
     * Configura el Bottom Navigation para navegar entre secciones.
     * 
     * Establece el item actual como seleccionado y configura los
     * listeners para cambiar de Activity al seleccionar otro item.
     */
    private fun setupBottomNavigation() {
        binding.bottomNavigation.selectedItemId = R.id.nav_home

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true // Ya estamos aquí
                R.id.nav_search -> {
                    startActivity(Intent(this, SearchActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_add -> {
                    startActivity(Intent(this, AddRecipeActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_favorites -> {
                    startActivity(Intent(this, FavoritesActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Inserta recetas de ejemplo si la base de datos está vacía.
     * 
     * Se ejecuta solo en la primera ejecución para proporcionar
     * contenido inicial al usuario. Incluye 6 recetas de diferentes
     * categorías para demostrar la funcionalidad de la app.
     */
    private fun insertSampleRecipes() {
        recipeViewModel.allRecipes.observe(this) { recipes ->
            if (recipes.isEmpty()) {
                // Lista de recetas de ejemplo
                val sampleRecipes = listOf(
                    Recipe(
                        name = "Tacos al Pastor",
                        category = "Mexicana",
                        time = "30 min",
                        servings = 4,
                        difficulty = "Fácil",
                        ingredients = "Carne de cerdo,Piña,Tortillas,Cilantro,Cebolla,Limón",
                        instructions = "1. Marinar la carne con especias\n2. Asar la carne hasta dorar\n3. Calentar las tortillas\n4. Servir con piña, cilantro y cebolla",
                        imageUrl = "🌮"
                    ),
                    // ... más recetas
                )

                // Insertar cada receta en la base de datos
                sampleRecipes.forEach { recipe ->
                    recipeViewModel.insert(recipe)
                }
            }
        }
    }
}
```

---

### 6. RecipeAdapter - Adapter del RecyclerView
```kotlin
package com.recetas.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.recetas.app.R
import com.recetas.app.data.model.Recipe

/**
 * Adapter para mostrar recetas en un RecyclerView.
 * 
 * Convierte los datos de Recipe en vistas visuales y maneja
 * la interacción del usuario con cada item de la lista.
 * Implementa el patrón ViewHolder para optimizar el rendimiento.
 *
 * @property onItemClick Lambda que se ejecuta al hacer click en una receta
 * 
 * @author Cristian y David
 * @since 1.0
 */
class RecipeAdapter(
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter() {

    /**
     * Lista de recetas a mostrar.
     * Inicialmente vacía, se actualiza con setRecipes().
     */
    private var recipes = emptyList()

    /**
     * ViewHolder que mantiene las referencias a las vistas de cada item.
     * 
     * Evita llamadas repetidas a findViewById mejorando el rendimiento.
     * Se crea una vez y se reutiliza cuando el item sale y vuelve a pantalla.
     * 
     * @param itemView Vista raíz del item inflado
     */
    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.recipeNameTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.recipeTimeTextView)
        val servingsTextView: TextView = itemView.findViewById(R.id.recipeServingsTextView)
        val emojiTextView: TextView = itemView.findViewById(R.id.recipeEmoji)
    }

    /**
     * Crea un nuevo ViewHolder inflando el layout del item.
     * 
     * Se llama cuando RecyclerView necesita un nuevo ViewHolder
     * porque no hay ninguno reciclable disponible.
     * 
     * @param parent ViewGroup padre donde se añadirá la vista
     * @param viewType Tipo de vista (no usado aquí, todos son iguales)
     * @return Nuevo ViewHolder con la vista inflada
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    /**
     * Vincula los datos de una receta con las vistas del ViewHolder.
     * 
     * Se llama cuando RecyclerView necesita mostrar un item en pantalla.
     * Actualiza las vistas con los datos de la receta en la posición indicada.
     * 
     * @param holder ViewHolder cuyas vistas deben ser actualizadas
     * @param position Posición del item en la lista
     */
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        // Obtener la receta en esta posición
        val currentRecipe = recipes[position]

        // Actualizar las vistas con los datos
        holder.nameTextView.text = currentRecipe.name
        holder.timeTextView.text = currentRecipe.time
        holder.servingsTextView.text = currentRecipe.servings.toString()
        holder.emojiTextView.text = currentRecipe.imageUrl ?: "🍽️"

        // Configurar click listener para toda la vista
        holder.itemView.setOnClickListener {
            onItemClick(currentRecipe)
        }
    }

    /**
     * Retorna el número total de items en la lista.
     * 
     * @return Cantidad de recetas a mostrar
     */
    override fun getItemCount() = recipes.size

    /**
     * Actualiza la lista de recetas y notifica al RecyclerView.
     * 
     * Debe ser llamado desde la Activity/Fragment cuando cambian los datos.
     * notifyDataSetChanged() indica al RecyclerView que redibuje todos los items.
     * 
     * @param recipes Nueva lista de recetas a mostrar
     */
    fun setRecipes(recipes: List) {
        this.recipes = recipes
        notifyDataSetChanged()
    }
}
```

---
---

### 7. AppDatabase - Configuración de Room Database
```kotlin
package com.recetas.app.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.recetas.app.data.local.dao.*
import com.recetas.app.data.model.*

/**
 * Clase abstracta que define la configuración de la base de datos Room.
 * 
 * Room genera automáticamente la implementación de esta clase.
 * Actúa como punto de acceso principal para la base de datos subyacente.
 * Implementa el patrón Singleton para garantizar una única instancia.
 * 
 * La base de datos contiene 8 entidades relacionadas para gestionar
 * recetas, calificaciones, colecciones, tags, notificaciones y multimedia.
 * 
 * @property entities Lista de clases Entity que definen las tablas
 * @property version Número de versión del esquema de BD (incrementar en cambios)
 * @property exportSchema Si se debe exportar el esquema a archivo JSON
 * 
 * @author Cristian y David
 * @since 1.0
 */
@Database(
    entities = [
        Recipe::class,              // Tabla de recetas
        Rating::class,              // Tabla de calificaciones
        RecipeCollection::class,    // Tabla de colecciones
        RecipeCollectionItem::class,// Items de colecciones (relación M-N)
        Tag::class,                 // Tabla de etiquetas
        RecipeTag::class,           // Relación recetas-tags (M-N)
        Notification::class,        // Tabla de notificaciones
        RecipeMedia::class          // Tabla de multimedia (fotos/videos)
    ],
    version = 7,                    // Versión actual del esquema
    exportSchema = false            // No exportar esquema a JSON
)
@TypeConverters(Converters::class)  // Convertidores de tipos personalizados
abstract class AppDatabase : RoomDatabase() {
    
    /**
     * Proporciona acceso al DAO de recetas.
     * @return Instancia del RecipeDao
     */
    abstract fun recipeDao(): RecipeDao
    
    /**
     * Proporciona acceso al DAO de calificaciones.
     * @return Instancia del RatingDao
     */
    abstract fun ratingDao(): RatingDao
    
    /**
     * Proporciona acceso al DAO de colecciones.
     * @return Instancia del CollectionDao
     */
    abstract fun collectionDao(): CollectionDao
    
    /**
     * Proporciona acceso al DAO de notificaciones.
     * @return Instancia del NotificationDao
     */
    abstract fun notificationDao(): NotificationDao
    
    /**
     * Proporciona acceso al DAO de tags.
     * @return Instancia del TagDao
     */
    abstract fun tagDao(): TagDao
    
    /**
     * Proporciona acceso al DAO de multimedia.
     * @return Instancia del RecipeMediaDao
     */
    abstract fun recipeMediaDao(): RecipeMediaDao

    /**
     * Objeto companion que implementa el patrón Singleton.
     * 
     * Garantiza que solo exista una instancia de la base de datos
     * en toda la aplicación, evitando problemas de concurrencia.
     */
    companion object {
        /**
         * Instancia única de la base de datos.
         * @Volatile asegura que los cambios sean visibles inmediatamente
         * en todos los hilos de ejecución.
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Obtiene la instancia única de la base de datos.
         * 
         * Implementa double-checked locking para thread-safety.
         * Si la instancia no existe, la crea de forma sincronizada
         * para evitar que múltiples hilos creen instancias duplicadas.
         * 
         * @param context Contexto de la aplicación
         * @return Instancia única de AppDatabase
         */
        fun getDatabase(context: Context): AppDatabase {
            // Si la instancia ya existe, retornarla directamente
            return INSTANCE ?: synchronized(this) {
                // Double-check dentro del bloque sincronizado
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "recipe_database"  // Nombre del archivo de la BD
                )
                .fallbackToDestructiveMigration()  // Recrear BD en cambios de esquema
                .build()
                
                // Guardar la instancia y retornarla
                INSTANCE = instance
                instance
            }
        }
    }
}
```

---

### 8. DetailActivity - Pantalla de Detalle de Receta
```kotlin
package com.recetas.app.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.RatingBar
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.recetas.app.adapters.RatingAdapter
import com.recetas.app.adapters.RecipeDetailPagerAdapter
import com.recetas.app.data.model.Recipe
import com.recetas.app.data.model.Rating
import com.recetas.app.databinding.ActivityDetailBinding
import com.recetas.app.ui.add.EditRecipeActivity
import com.recetas.app.ui.home.RecipeViewModel
import com.recetas.app.ui.shopping.ShoppingListActivity

/**
 * Activity que muestra el detalle completo de una receta.
 * 
 * Presenta toda la información de la receta incluyendo:
 * - Header con emoji, nombre y categoría
 * - Cards de información (tiempo, porciones, dificultad)
 * - Tabs con ingredientes y pasos de preparación
 * - Sistema de calificaciones con promedio y comentarios
 * - Botones de acción (favorito, editar, lista de compras, etc.)
 * 
 * Implementa arquitectura MVVM con observación de LiveData.
 *
 * @author Cristian y David
 * @since 1.0
 */
class DetailActivity : AppCompatActivity() {

    /**
     * ViewBinding para acceso seguro a las vistas del layout.
     */
    private lateinit var binding: ActivityDetailBinding
    
    /**
     * ViewModel para gestionar datos de recetas.
     */
    private lateinit var recipeViewModel: RecipeViewModel
    
    /**
     * ViewModel para gestionar calificaciones y reseñas.
     */
    private lateinit var ratingViewModel: RatingViewModel
    
    /**
     * Adapter para mostrar la lista de reseñas.
     */
    private lateinit var ratingAdapter: RatingAdapter
    
    /**
     * ID de la receta actual obtenido del Intent.
     */
    private var recipeId: Int = 0
    
    /**
     * Referencia a la receta actual cargada.
     */
    private var currentRecipe: Recipe? = null

    /**
     * Inicializa la Activity y carga los datos de la receta.
     * 
     * @param savedInstanceState Estado guardado de la instancia anterior
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener ID de la receta del Intent
        recipeId = intent.getIntExtra("RECIPE_ID", 0)

        // Inicializar ViewModels
        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]
        ratingViewModel = ViewModelProvider(this)[RatingViewModel::class.java]

        // Configurar toolbar con botón de retroceso
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        // Configurar navegación hacia atrás
        binding.toolbar.setNavigationOnClickListener { finish() }

        // Configurar RecyclerView de reseñas
        setupRatingsRecyclerView()

        // Cargar datos de la receta y sus reseñas
        loadRecipeData()
        loadRatings()

        // Configurar listeners de botones
        setupClickListeners()
    }

    /**
     * Configura el RecyclerView para mostrar las reseñas.
     * 
     * Usa LinearLayoutManager para lista vertical de reseñas.
     */
    private fun setupRatingsRecyclerView() {
        ratingAdapter = RatingAdapter()
        binding.ratingsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.ratingsRecyclerView.adapter = ratingAdapter
    }

    /**
     * Carga y muestra los datos de la receta desde la base de datos.
     * 
     * Observa los cambios en el LiveData y actualiza la UI
     * automáticamente cuando la receta cambia.
     */
    private fun loadRecipeData() {
        recipeViewModel.getRecipeById(recipeId).observe(this) { recipe ->
            if (recipe != null) {
                currentRecipe = recipe

                // Actualizar vistas del header
                binding.recipeEmojiDetail.text = recipe.imageUrl ?: "🍽️"
                binding.recipeNameDetail.text = recipe.name
                binding.recipeCategoryDetail.text = recipe.category
                
                // Actualizar cards de información
                binding.recipeTimeDetail.text = recipe.time
                binding.recipeServingsDetail.text = recipe.servings.toString()
                binding.recipeDifficultyDetail.text = recipe.difficulty

                // Actualizar estado del botón de favorito
                updateFavoriteIcon(recipe.isFavorite)

                // Configurar ViewPager con tabs de ingredientes/preparación
                setupViewPager(recipe.ingredients, recipe.instructions)
            }
        }
    }

    /**
     * Carga las reseñas de la receta y calcula el promedio.
     * 
     * Observa los cambios en las calificaciones y actualiza
     * tanto la lista de reseñas como el promedio de estrellas.
     */
    private fun loadRatings() {
        ratingViewModel.getRatingsByRecipe(recipeId).observe(this) { ratings ->
            if (ratings.isNotEmpty()) {
                // Actualizar adapter con las reseñas
                ratingAdapter.setRatings(ratings)

                // Mostrar número de reseñas
                binding.ratingsCountText.text =
                    "${ratings.size} reseña${if (ratings.size > 1) "s" else ""}"

                // Calcular y mostrar promedio
                val average = ratings.map { it.stars }.average().toFloat()
                binding.averageRatingText.text = String.format("%.1f", average)
                binding.averageRatingBar.rating = average
            } else {
                // Sin reseñas aún
                binding.ratingsCountText.text = "Sin reseñas aún"
                binding.averageRatingText.text = "0.0"
                binding.averageRatingBar.rating = 0f
            }
        }
    }

    /**
     * Configura todos los listeners de click de los botones.
     * 
     * Incluye: favorito, editar, lista de compras, calificar,
     * gestionar tags, recordatorios y galería de fotos.
     */
    private fun setupClickListeners() {
        // Botón de favorito
        binding.favoriteButton.setOnClickListener { 
            toggleFavorite() 
        }

        // Botón de editar
        binding.editButton.setOnClickListener {
            val intent = Intent(this, EditRecipeActivity::class.java)
            intent.putExtra("RECIPE_ID", recipeId)
            startActivity(intent)
        }

        // Botón de lista de compras
        binding.shoppingListButton.setOnClickListener {
            val intent = Intent(this, ShoppingListActivity::class.java)
            intent.putExtra("RECIPE_ID", recipeId)
            startActivity(intent)
        }

        // Botón de calificar
        binding.rateButton.setOnClickListener {
            showRatingDialog()
        }

        // Botón de gestionar tags
        binding.manageTagsButton.setOnClickListener {
            showManageTagsDialog()
        }

        // Botón de recordatorio
        binding.reminderButton.setOnClickListener {
            showCreateReminderDialog()
        }

        // Botón de galería
        binding.galleryButton.setOnClickListener {
            // Navegar a galería de fotos
            // (implementación omitida para brevedad)
        }
    }

    /**
     * Alterna el estado de favorito de la receta.
     * 
     * Cambia el valor de isFavorite y actualiza la base de datos.
     * Muestra un Toast confirmando la acción.
     */
    private fun toggleFavorite() {
        currentRecipe?.let { recipe ->
            // Crear copia con estado de favorito invertido
            val updatedRecipe = recipe.copy(isFavorite = !recipe.isFavorite)
            
            // Actualizar en base de datos
            recipeViewModel.update(updatedRecipe)
            
            // Actualizar referencia local
            currentRecipe = updatedRecipe

            // Actualizar UI
            updateFavoriteIcon(updatedRecipe.isFavorite)

            // Mostrar mensaje de confirmación
            val message = if (updatedRecipe.isFavorite) {
                "Agregado a favoritos ❤️"
            } else {
                "Eliminado de favoritos"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Actualiza el icono del botón de favorito según el estado.
     * 
     * @param isFavorite True si la receta es favorita, false si no
     */
    private fun updateFavoriteIcon(isFavorite: Boolean) {
        // Aquí puedes cambiar el icono o color del botón
        // (implementación específica de UI)
    }

    /**
     * Configura el ViewPager2 con tabs de ingredientes y preparación.
     * 
     * Crea un adapter personalizado que muestra dos páginas:
     * una con la lista de ingredientes y otra con los pasos.
     * 
     * @param ingredients String con ingredientes separados por comas
     * @param instructions String con pasos de preparación
     */
    private fun setupViewPager(ingredients: String, instructions: String) {
        binding.viewPager.visibility = View.VISIBLE
        binding.tabLayout.visibility = View.VISIBLE

        // Convertir string de ingredientes a lista
        val ingredientsList = ingredients.split(",").map { it.trim() }

        // Crear adapter del ViewPager
        val adapter = RecipeDetailPagerAdapter(ingredientsList, instructions)
        binding.viewPager.adapter = adapter

        // Vincular TabLayout con ViewPager
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Ingredientes"
                1 -> "Preparación"
                else -> ""
            }
        }.attach()
    }

    /**
     * Muestra un diálogo para que el usuario califique la receta.
     * 
     * El diálogo incluye:
     * - RatingBar para seleccionar estrellas (1-5)
     * - EditText para comentario opcional
     * - Botones de Enviar y Cancelar
     */
    private fun showRatingDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Califica esta receta")

        // Crear layout del diálogo
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        // RatingBar para estrellas
        val ratingBar = RatingBar(this).apply {
            numStars = 5
            stepSize = 1f
        }

        // EditText para comentario
        val commentInput = EditText(this).apply {
            hint = "Escribe tu comentario (opcional)"
            minLines = 3
        }

        layout.addView(ratingBar)
        layout.addView(commentInput)
        builder.setView(layout)

        // Botón de enviar
        builder.setPositiveButton("Enviar") { _, _ ->
            // Obtener nombre del usuario de SharedPreferences
            val prefs = getSharedPreferences("RecetAppPrefs", MODE_PRIVATE)
            val userName = prefs.getString("name", "Usuario") ?: "Usuario"

            // Crear objeto Rating
            val rating = Rating(
                recipeId = recipeId,
                userName = userName,
                stars = ratingBar.rating.toInt(),
                comment = commentInput.text.toString()
            )

            // Guardar en base de datos
            ratingViewModel.addRating(rating)
            
            Toast.makeText(this, "¡Valoración enviada!", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Cancelar", null)
        builder.show()
    }

    /**
     * Muestra diálogo para gestionar tags de la receta.
     * 
     * Permite seleccionar/deseleccionar tags existentes para
     * asociarlos con la receta actual.
     * (Implementación simplificada)
     */
    private fun showManageTagsDialog() {
        // Implementación del diálogo de tags
        Toast.makeText(this, "Gestionar Tags", Toast.LENGTH_SHORT).show()
    }

    /**
     * Muestra diálogo para crear un recordatorio de la receta.
     * 
     * Permite configurar fecha, hora y mensaje personalizado
     * para recibir una notificación futura.
     * (Implementación simplificada)
     */
    private fun showCreateReminderDialog() {
        // Implementación del diálogo de recordatorio
        Toast.makeText(this, "Crear Recordatorio", Toast.LENGTH_SHORT).show()
    }

    /**
     * Se llama cuando la Activity vuelve a primer plano.
     * 
     * Recarga los datos por si fueron modificados en otra pantalla.
     */
    override fun onResume() {
        super.onResume()
        loadRecipeData()
    }
}
```

---

### 9. AddRecipeActivity - Agregar Nueva Receta
```kotlin
package com.recetas.app.ui.add

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.recetas.app.data.model.Recipe
import com.recetas.app.databinding.ActivityAddRecipeBinding
import com.recetas.app.ui.home.MainActivity
import com.recetas.app.ui.home.RecipeViewModel

/**
 * Activity para agregar una nueva receta a la base de datos.
 * 
 * Proporciona un formulario completo con validación para que
 * el usuario ingrese todos los datos de una receta nueva:
 * - Nombre y emoji representativo
 * - Tiempo de preparación y porciones
 * - Categoría y dificultad (dropdowns)
 * - Ingredientes (separados por comas)
 * - Pasos de preparación
 * 
 * Implementa validaciones para garantizar que todos los campos
 * requeridos estén completos antes de guardar.
 *
 * @author Cristian y David
 * @since 1.0
 */
class AddRecipeActivity : AppCompatActivity() {

    /**
     * ViewBinding para acceso seguro a las vistas.
     */
    private lateinit var binding: ActivityAddRecipeBinding
    
    /**
     * ViewModel para insertar la receta en la BD.
     */
    private lateinit var recipeViewModel: RecipeViewModel
    
    /**
     * Emoji seleccionado para representar la receta.
     * Por defecto es el emoji de plato genérico.
     */
    private var selectedEmoji = "🍽️"

    /**
     * Lista de categorías disponibles para el dropdown.
     */
    private val categories = listOf(
        "Mexicana", "Italiana", "Japonesa", "Americana", 
        "Ensaladas", "Postres", "Sopas", "Bebidas"
    )
    
    /**
     * Lista de niveles de dificultad para el dropdown.
     */
    private val difficulties = listOf("Fácil", "Media", "Difícil")
    
    /**
     * Lista de emojis disponibles para seleccionar.
     */
    private val emojis = listOf(
        "🌮", "🍕", "🍝", "🍣", "🍔", "🥗", "🍲", "🥘", 
        "🍛", "🍜", "🥙", "🌯", "🥪", "🍱", "🍳", "🥞", 
        "🧇", "🥓", "🍗", "🍖"
    )

    /**
     * Inicializa la Activity y configura todos los componentes.
     * 
     * @param savedInstanceState Estado guardado de la instancia anterior
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar ViewModel
        recipeViewModel = ViewModelProvider(this)[RecipeViewModel::class.java]

        // Configurar dropdowns de categoría y dificultad
        setupCategoryDropdown()
        setupDifficultyDropdown()

        // Configurar listeners
        setupClickListeners()

        // Configurar Bottom Navigation
        setupBottomNavigation()
    }

    /**
     * Configura el AutoCompleteTextView de categorías.
     * 
     * Crea un ArrayAdapter con las categorías disponibles
     * y lo asigna al campo de categoría.
     */
    private fun setupCategoryDropdown() {
        val adapter = ArrayAdapter(
            this, 
            android.R.layout.simple_dropdown_item_1line, 
            categories
        )
        binding.recipeCategoryInput.setAdapter(adapter)
    }

    /**
     * Configura el AutoCompleteTextView de dificultad.
     * 
     * Crea un ArrayAdapter con los niveles de dificultad
     * y lo asigna al campo de dificultad.
     */
    private fun setupDifficultyDropdown() {
        val adapter = ArrayAdapter(
            this, 
            android.R.layout.simple_dropdown_item_1line, 
            difficulties
        )
        binding.recipeDifficultyInput.setAdapter(adapter)
    }

    /**
     * Configura los listeners de click de todos los botones.
     */
    private fun setupClickListeners() {
        // Click en el área de imagen para elegir emoji
        binding.imageCard.setOnClickListener {
            showEmojiPicker()
        }

        // Botón volver
        binding.backButton.setOnClickListener {
            finish()
        }

        // Botón guardar receta
        binding.saveRecipeButton.setOnClickListener {
            saveRecipe()
        }
    }

    /**
     * Muestra un diálogo para seleccionar un emoji.
     * 
     * Presenta la lista de emojis disponibles en un AlertDialog
     * y actualiza la vista preview cuando el usuario selecciona uno.
     */
    private fun showEmojiPicker() {
        AlertDialog.Builder(this)
            .setTitle("Elige un emoji para tu receta")
            .setItems(emojis.toTypedArray()) { _, which ->
                selectedEmoji = emojis[which]
                binding.emojiPreview.text = selectedEmoji
            }
            .show()
    }

    /**
     * Valida los campos y guarda la receta en la base de datos.
     * 
     * Realiza validaciones de:
     * - Campos no vacíos
     * - Selección de dropdown
     * - Número de porciones válido
     * 
     * Si todo es válido, crea el objeto Recipe y lo inserta
     * mediante el ViewModel, luego navega al inicio.
     */
    private fun saveRecipe() {
        // Obtener valores de los campos
        val name = binding.recipeNameInput.text.toString().trim()
        val time = binding.recipeTimeInput.text.toString().trim()
        val servingsStr = binding.recipeServingsInput.text.toString().trim()
        val category = binding.recipeCategoryInput.text.toString().trim()
        val difficulty = binding.recipeDifficultyInput.text.toString().trim()
        val ingredients = binding.recipeIngredientsInput.text.toString().trim()
        val instructions = binding.recipeInstructionsInput.text.toString().trim()

        // Validar nombre
        if (name.isEmpty()) {
            binding.recipeNameInput.error = "El nombre es requerido"
            return
        }

        // Validar tiempo
        if (time.isEmpty()) {
            binding.recipeTimeInput.error = "El tiempo es requerido"
            return
        }

        // Validar porciones
        if (servingsStr.isEmpty()) {
            binding.recipeServingsInput.error = "Las porciones son requeridas"
            return
        }

        // Validar categoría
        if (category.isEmpty()) {
            Toast.makeText(this, "Selecciona una categoría", Toast.LENGTH_SHORT).show()
            return
        }

        // Validar dificultad
        if (difficulty.isEmpty()) {
            Toast.makeText(this, "Selecciona una dificultad", Toast.LENGTH_SHORT).show()
            return
        }

        // Validar ingredientes
        if (ingredients.isEmpty()) {
            binding.recipeIngredientsInput.error = "Los ingredientes son requeridos"
            return
        }

        // Validar preparación
        if (instructions.isEmpty()) {
            binding.recipeInstructionsInput.error = "La preparación es requerida"
            return
        }

        // Convertir porciones a número
        val servings = servingsStr.toIntOrNull() ?: 0
        if (servings <= 0) {
            binding.recipeServingsInput.error = "Número inválido"
            return
        }

        // Crear objeto Recipe
        val newRecipe = Recipe(
            name = name,
            category = category,
            time = time,
            servings = servings,
            difficulty = difficulty,
            ingredients = ingredients,
            instructions = instructions,
            imageUrl = selectedEmoji,
            isFavorite = false
        )

        // Guardar en la base de datos
        recipeViewModel.insert(newRecipe)

        // Mostrar mensaje de éxito
        Toast.makeText(this, "Receta guardada exitosamente ✅", Toast.LENGTH_SHORT).show()

        // Volver a la pantalla principal
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }

    /**
     * Configura el Bottom Navigation con el item actual seleccionado.
     * 
     * Permite navegar a otras secciones de la app desde esta pantalla.
     */
    private fun setupBottomNavigation() {
        binding.bottomNavigation.selectedItemId = com.recetas.app.R.id.nav_add

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                com.recetas.app.R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                com.recetas.app.R.id.nav_search -> {
                    // Navegar a búsqueda
                    true
                }
                com.recetas.app.R.id.nav_add -> true // Ya estamos aquí
                com.recetas.app.R.id.nav_favorites -> {
                    // Navegar a favoritos
                    true
                }
                com.recetas.app.R.id.nav_profile -> {
                    // Navegar a perfil
                    true
                }
                else -> false
            }
        }
    }
}
```

---


🤝 Contribuir
¡Las contribuciones son bienvenidas! Si deseas mejorar RecetApp, sigue estos pasos:
1. Fork del Repositorio
bash# Haz clic en "Fork" en GitHub
# Luego clona tu fork
git clone https://github.com/1224100540cujl-commits/AppRecetasFinal.git
cd RecetApp
2. Crear una Rama
bash# Crea una rama para tu feature o bugfix
git checkout -b feature/nueva-funcionalidad

# O para un bugfix
git checkout -b fix/corregir-bug
3. Hacer Cambios

Escribe código limpio y bien documentado
Sigue las convenciones de Kotlin
Agrega comentarios KDoc para funciones públicas
Asegúrate de que compila sin errores

4. Commit y Push
bash# Agrega tus cambios
git add .

# Commit con mensaje descriptivo
git commit -m "feat: agregar búsqueda por tags"

# Push a tu fork
git push origin feature/nueva-funcionalidad
5. Pull Request

Ve a tu fork en GitHub
Haz clic en "Pull Request"
Describe tus cambios detalladamente
Espera revisión y feedback


Convenciones de Código
Nombres de Archivos

Activities: *Activity.kt (ej: MainActivity.kt)
ViewModels: *ViewModel.kt (ej: RecipeViewModel.kt)
Adapters: *Adapter.kt (ej: RecipeAdapter.kt)
Fragments: *Fragment.kt

Comentarios KDoc
kotlin/**
 * Inserta una nueva receta en la base de datos.
 *
 * @param recipe La receta a insertar
 * @return El ID de la receta insertada
 * @throws SQLException Si hay un error en la inserción
 */
@Insert
suspend fun insert(recipe: Recipe): Long
```

#### Estilo de Código
- Indentación: 4 espacios
- Líneas: máximo 120 caracteres
- Imports: organizar alfabéticamente
- Nombres: camelCase para funciones, PascalCase para clases

---

### Áreas de Mejora

Algunas ideas para contribuir:

#### Funcionalidades
- [ ] Sincronización con Firebase
- [ ] Modo offline completo
- [ ] Export/Import de recetas (JSON)
- [ ] Widget para la pantalla de inicio
- [ ] Modo oscuro
- [ ] Soporte multiidioma (inglés, francés)
- [ ] Integración con APIs de recetas
- [ ] Temporizador de cocina integrado
- [ ] Conversor de unidades de medida
- [ ] Planificador semanal de menús

#### Mejoras UI/UX
- [ ] Animaciones más fluidas
- [ ] Transiciones entre pantallas
- [ ] Gestos swipe para acciones rápidas
- [ ] Tutorial inicial para nuevos usuarios
- [ ] Mejores placeholders y estados vacíos

#### Testing
- [ ] Unit tests para ViewModels
- [ ] UI tests con Espresso
- [ ] Integration tests para Room
- [ ] Tests de navegación

#### Performance
- [ ] Paginación en listas largas
- [ ] Caché de imágenes mejorado
- [ ] Reducir tamaño del APK
- [ ] Optimizar consultas de Room

---

## 📄 Licencia

Este proyecto está licenciado bajo la **Licencia MIT**.
```
MIT License

Copyright (c) 2025 Cristian Uriel Juarez Lopez y Diego David del Angel Sanchez - RecetApp

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

📧 Contacto
Desarrolladores 4181445620 y 4272241382

Cristian y David
GitHub: @1224100540cujl-commits  @crizzz77

Links del Proyecto

Repositorio: https://github.com/1224100540cujl-commits/AppRecetasFinal.git

Reportar Bugs
Si encuentras un bug, por favor:

Descripción clara del problema
Pasos para reproducir
Capturas de pantalla (si aplica)
Versión de Android
Modelo de dispositivo

🙏 Agradecimientos

Material Design 3 por los componentes UI modernos
Google Jetpack por las librerías de arquitectura
Kotlin por el lenguaje maravilloso
Android Developers por la excelente documentación
Comunidad de Stack Overflow por resolver dudas
Todos los contribuidores que hacen posible este proyecto

Hecho con ❤️ y ☕ por Cristian y David
Si te gusta el proyecto, ¡dale una ⭐ en GitHub!
