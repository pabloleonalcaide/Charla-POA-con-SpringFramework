#  Notas Java (Inversión de Control & Inyección de Dependencias)

ApplicationContext es una sub-interfaz de beanFactory. representa el contenedor Spring

##  IoC 

Se delega a otro la creación de clases, sólo defines cómo se crea, invirtiendo el flujo respecto a los métodos de programación tradicionales. Para poder implementarla necesitamos un agente externo (contenedor) que se encarga de realizar las conexiones necesarias entre las clases dependientes.


### Inyección de Dependencias

El Contenedor (ApplicationContext) inyecta las dependencias necesarias en cada clase, para reducir el acoplamiento entre clases. Spring utiliza la IdD como método de IoC.
El contenedor inyecta las dependencias cuando se crea el bean.
El objeto no busca sus dependencias y no conoce la ubicación o clase de las dependencias.
Las clases son más fáciles de probar, en particular cuando las dependencias están en las interfaces.

Para arrancar nuestro contenedor de Spring podemos hacerlo de una manera 'centrada en XML' (`ClassPathXmlApplicationContext`) o una forma 'centrada en Java' usando `AnnotationConfigApplicationContext` y la anotación @ImportResource para importar XML según sea necesario.

En Spring, los objetos que forman el eje de la aplicación y que son administrados por el contenedor Spring IoC se denominan Beans.
Los metadatos dan la configuración para que se creen las instancias de los objetos necesarios.
    
* formato xml
* anotaciones
* basada en Java (A partir de la v3  => JavaConfig) 

La configuración de Spring consiste en al menos una (o mas) de una definición de bean que el contenedor debe administrar.

#### Spring DI Basada en Constructor

El DI basado en el constructor se logra mediante el contenedor invocando a un constructor con ninguno o varios argumentos, cada uno representando una dependencia.
La coincidencia de resolución de argumentos del constructor se produce utilizando el tipo de argumento. Ante ambigüedades, se puede utilizar la coincidencia por tipo (type) o por índice (index)    

```xml
    <!-- Resolución de ambigüedad por índice-->
    <bean id="example1" class="ex.bean1">
        <constructor-arg index="0" value="333">
        <constructor-arg index="1" value="555">
    </bean>
    <!-- Resolución de ambigüedad por tipo-->
   <bean id="example2" class="ex.bean2">
        <constructor-arg type="int" value="333"> 
        <constructor-arg type="java.lang.String" value="555"> 
   </bean>
```

#### Spring DI Basada en Propiedades

El contenedor llama a los métodos de establecimiento en sus beans después de invocar un constructor sin argumentos o un método estático de fábrica sin argumentos para crear una instancia de su bean.

```xml
<bean id="myDataSource" class="org.apache.com...">
    <property name="driverClassName" value="com...">
    <property name="url" value="jdbc:mysql:...">
    <property name="username" value="root">
    <property name="password" value="secret1234">
</bean>
```

```xml
<bean id="Profile" class="com.foo.profile">
    <property name="id" value="1234">
    <property name="target" >
        <bean class="com.foo.Person"> <!-- Bean en propiedades-->
            <property name="name" value="john doe">
            <property name="role" value="admin">
            <property name="key" <null/> ></property> 
        </bean>
    </property>
    <property name="phoneMap"> <!-- Lista (Map) en propiedades -->
        <map>
            <entry key="0" value="662123456">
            <entry key="1" value="662112233">
        </map>
    </property>
</bean>
```

#### Espacios de nombres P y C

* El espacio de Nombres P permite usar los atributos del bean en el elemento bean en lugar de usar elementos property para describir los valores de sus propiedades y/o beans de colaboración:
```xml
<bean name="john-doe"
    class= "com.example.Person"
    p:name = "john doe"
    p:spouse-ref = "jane"
/>
```

* El espacio de nombres c permite el uso de atributos en línea para configurar los argumentos del constructor en lugar de elmentos `constructor-arg` anidados
```xml
<bean id="paa"
    c:pe-ref="var1"
    c:pi-ref="var2"
    c:email="me@paa.com"
/>
```

#### AutoWiring
El ApplicationContext puede resolver de forma automática las clases colaboradoras de otras (Autowiring -> Cableado automático)
 Tipos : [{no: No Autowiring},{byName: Por nombre de la propiedad},{byType: Por tipo de propiedad},{constructor: Análogo a byType pero se aplica a argumentos del constructor}]
* Ventajas
    * Puede reducir significativamente la necesidad de especificar propiedades o argumentos de consructor
    * Autowiring puede actualizar una config a medida que los objetos evolucionen
* Desventajas
    * Las dependencias explícitas property y constructor-arg siempre anulan  el cableado automático
    * El Autowiring es menos exacto que el cableado explícito: las relaciones entre sus objetos administrados  por Spring ya no están docuementadas explícitamente.
    * Colisiones: múltiples definiciones de beans dentro del contenedor puedn coincidir con el tipo especificado por el método de establecimiento o el argumento del constructor para ser autowired

La etiqueta Autowired se puede aplicar a los setter, métodos con nombres arbitrarios  con/sin argumentos, colecciones , constructores y atributos
Sólo un constructor de los disponibles puede ser anotado con @autowired
Spring proporciona anotaciones de estereotipos: @Component, @Repository, @Controller... Todas son especializaciones de @Component que se adaptan más adecuadamente para su procesamiento por herramientas o asociarse con aspectos.
Para detectar estas clases y registrar los beans correspondientes, se debe incluir  un elemento XML de definición
 `<context: component-scan base-package="org.example">`
