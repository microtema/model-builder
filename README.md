# model-builder
Reducing Boilerplate Code with Annotation Driven Tests
> More Time for Feature functionality
  Through a simple set of annotations and saving 60% of development time 

## How to use programmatically

> ModelBuilder < Person > builder = new PersonBuilder();

> or on the fly

> ModelBuilder < Person > builder = ModelBuilderFactory.createBuilder(Person.class);
 
* create person instance within filled required properties
    * **Person person = builder.min();**
 
* create person instance within all properties
    * **Person person = builder.max();**
  
* create person instance within filled required or all properties
    * **Person person = builder.mix();**

* create person within fix values
    * **Person person = builder.fix();**

* create person from Resource
    * **Person person = builder.fromResource("file://var/person.xml");**
  
* create list of person from Resource
    * **List< Person > persons = builder.listFromResource("file://var/persons.json");**

* create Set of person from Resource
  * **Set< Person > persons = builder.setFromResource("file://var/persons.json");**

* create a List of person instances within filled required with properties random **[1..10]** size
    * **List< Person > persons = builder.list();**

* create a List of person instances within filled required properties with fixed size
    * **List< Person > persons =  builder.list(100);**

* create a Set of person instances within filled required properties with random **[1..10]** size
    * **Set< Person > persons =  builder.set();**

* create a Set of person instances within filled required properties with fixed size
    * **Set< Person > persons = builder.set(100);**
    
## How to use with annotations?

*  @Model Person person; // create person instance within filled required properties
*  @Models Collection< Person > persons; // create a List of person instances with random **[1..10]** size
  
## How to use with annotations but with custom builder?

*  @Resource PersonModelBuilder builder; //custom model builder
*  @Model Person person; // create person instance within filled required properties
*  @Models Collection< Person > persons; // create a List of person instances with random **[1..10]** size

## How Annotated fields are injected?

>  @Before public void setUp() {
>      FieldInjectionUtil.injectFields(this);
>  }

## Supported out of the box types

* @Model Byte byte;
* @Model Boolean boolean;
* @Model Character character;
* @Model Date date;
* @Model BigDecimal bigDecimal;
* @Model Double double;
* @Model Float float;
* @Model Integer integer;
* @Model Long long;
* @Model String string;
* @Model URL url;
* @Model Map<K, V> map;
* @Model Enum enum;
    
## Technology Stack

* Java 1.8
    * Streams 
    * Lambdas
* Third Party Libraries
    * Commons-BeanUtils (Apache License)
    * Commons-IO (Apache License)
    * Commons-Lang3 (Apache License)
    * Jackson XML Databind (Apache License)
    * Junit (EPL 1.0 License)
    * lombok (MIT License)
* Code-Analyses
    * Sonar
    * Jacoco
    
## License

MIT (unless noted otherwise)

 