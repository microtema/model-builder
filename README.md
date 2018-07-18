# model-builder
Reducing Boilerplate Code with Annotation Driven Tests
> More Time for Feature functionality
  Through a simple set of annotations and saving 60% of development time 

## How to implement
public class PersonBuilder extends AbstractModelBuilder< Person > { }

## How to initialize

> ModelBuilder < Person > builder = new PersonBuilder();

or on the fly

> ModelBuilder < Person > builder = ModelBuilderFactory.createBuilder(Person.class);

## How to use programmatically
 
* create person instance within filled required fields
    * **Person person = builder.min();**
 
* create person instance within all fields
    * **Person person = builder.max();**
  
* create person instance within filled required or all fields
    * **Person person = builder.mix();**

* create person within fix values
    * **Person person = builder.fix();**

* create person from Resource
    * **Person person = builder.fromResource("file://var/person.xml");**

* create a List of person instances within filled required or all fields with random **[1..10]** size
    * **List<Person> persons = builder.list();**

* create a List of person instances within filled required or all fields with fixed size
    * **List<Person> persons =  builder.list(100);**

* create a Set of person instances within filled required or all fields with random **[1..10]** size
    * **Set<Person> persons =  builder.set();**

* create a Set of person instances within filled required or all fields with fixed size
    * **Set<Person> persons = builder.set(100);**
    
## How to use with annotations

*  @Model Person person; // create person instance within filled required fields
*  @Models Collection<Person> persons; // create a List of person instances within filled required or all fields with random **[1..10]** size
  
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
    
## Technology Stack

* Java 1.8
    * Streams 
    * Lambdas
* Third Party Libraries
    * Commons-BeanUtils (Apache License)
    * Commons-IO (Apache License)
    * Commons-Lang3 (Apache License)
    * Junit (EPL 1.0 License)
* Code-Analyses
    * Sonar
    * Jacoco

 