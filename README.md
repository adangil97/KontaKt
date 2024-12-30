# KontaKt

| [Android] | [iOS] |
|----------|----------|
| [Video](https://github.com/user-attachments/assets/2755b414-3405-44aa-a178-9eea91f077c7) | [Video](https://github.com/user-attachments/assets/b16a9072-f769-4054-b26d-cc5a66708326) |

<H2>Instrucciones para Clonar y Ejecutar el Proyecto KMP</H2>

<h2>Requisitos</h2>
<ul>
    <li><strong>Android Studio</strong> (con Android Lady Bug o posterior) para ejecutar la parte de Android.</li>
    <li><strong>MacBook Pro</strong> para ejecutar la parte de iOS debido a las restricciones de Xcode y el desarrollo para iOS.</li>
    <li><strong>Xcode</strong> instalado en tu MacBook Pro para el desarrollo de iOS.</li>
</ul>

<h2>Pasos</h2>
<h3>1. Clonar el Repositorio</h3>
<p>Abre una terminal y ejecuta el siguiente comando para clonar el repositorio:</p>
<pre><code>git clone https://github.com/adangil97/KontaKt.git</code></pre>

<h3>2. Abrir el Proyecto en Android Studio</h3>
<ul>
    <li>Navega a la carpeta del proyecto y abre el archivo <code>KontaKt</code> con <strong>Android Studio</strong>.</li>
    <li>Asegúrate de tener Android Lady Bug o posterior para evitar problemas de compatibilidad.</li>
</ul>

<h3>3. Configuración de Dependencias para Android</h3>
<ul>
    <li>Android Studio debería pedirte que sincronices las dependencias del proyecto automáticamente.</li>
    <li>Si no es así, puedes hacerlo manualmente desde la opción <strong>File > Sync Project with Gradle Files</strong>.</li>
</ul>

<H1>MVVM Clean Architecture</H1>

<H2>Capas de la arquitectura</H2>

![Android MVVM Clean Achitecture](https://cursokotlin.com/wp-content/uploads/2021/05/Android-Clean-Architecture.png)

<ol>
  <li>
    <p>Presentación (app)</p>
    <ul>
      <li><p>UI en general (Jetpack Compose): Se encargan de mostrar las vistas y navegación al usuario</p></li>
      <li><p>ViewModels: Realizan la comunicación con los casos de uso correspondientes.</p></li>
      <li><p>States, Effects, Actions: Forman Parte de la arquitectura para sellar contratos de llamadas y respuestas reactivas</p></li>
    </ul>
  </li>
  <li>
    <p>Usecases</p>
    <ul>
      <li><p>Contiene la separación de las funcionalidades disponibles (Principio de responsabilidad unica)</p></li>
    </ul>
  </li>
  <li>
    <p>Domain</p>
    <ul>
      <li><p>Contiene los modelos de negocio y los modelos a transferir a las capas superiores</p></li>
    </ul>
  </li>
  <li>
    <p>Data</p>
    <ul>
      <li><p>DataSources: Contiene las definiciones de los contratos de las fuentes de datos disponibles</p></li>
      <li>
        <p>
          Repositories: Son los encargados de validar cuando es apropiado usar cual data source o bien el mezclado de datos que los datasources proveen
        </p>        
      </li>
    </ul>
  </li>
  <li>
    <p>Framework (app)</p>
    <ul>
      <li><p>DataSources: Contiene las implementaciones de las fuentes de datos disponibles (Pueden ser remotos o locales, depende la abstracción)</p></li>
      <li><p>Module: Inyección de dependencias del repositorie de Data, y los DataSources a utilizar</p></li>
    </ul>
  </li>
<ol>

<h2>Librerías Utilizadas</h2>
<p>Este proyecto utiliza las siguientes librerías principales, cada una seleccionada por sus características específicas y beneficios para el desarrollo multiplataforma:</p>

<h2>SQLDelight</h2>
<p>Seleccionada por su estabilidad y excelente soporte multiplataforma. Esta librería provee:</p>
<ul>
    <li>Generación de código tipo-seguro para SQL</li>
    <li>Soporte nativo para múltiples plataformas</li>
    <li>Rendimiento optimizado</li>
</ul>

<h2>Koin</h2>
<p>Elegida por su amplia compatibilidad con Kotlin y facilidad para la inyección de dependencias. Ofrece:</p>
<ul>
    <li>Sintaxis pragmática y kotlin-friendly</li>
    <li>Configuración sencilla y rápida</li>
    <li>Excelente integración con otras librerías de Kotlin</li>
</ul>

<h2>Voyager</h2>
<p>Implementada por su excelente soporte y mantenimiento activo. Destacan:</p>
<ul>
    <li>Soporte robusto para navegación en Compose Multiplatform</li>
    <li>Actualizaciones frecuentes con nuevas integraciones</li>
    <li>Buena atención a issues y mantenimiento activo del proyecto</li>
    <li>Arquitectura bien definida para implementaciones multiplataforma</li>
</ul>

<h2>Peekaboo</h2>
<p>Integrada por su funcionalidad única en el ecosistema multiplataforma. A pesar de tener un soporte limitado, ofrece:</p>
<ul>
    <li>Acceso unificado a cámara y galería en Android e iOS</li>
    <li>Simplificación significativa en la implementación de funciones multimedia</li>
    <li>Solución práctica para aplicaciones que requieren capacidades multimedia multiplataforma</li>
</ul>
