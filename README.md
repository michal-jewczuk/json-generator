# JSON generator
## What does it do?
It can generate various json files: a single object or a list of objects.

## How does it work?
You have to set the structure of your desired json object by combining different elements together in *structure* panel.
To generate a list of objects you have to set the *number of objects to create* to desired level.
By default the file will be saved under *data.json* name in the location of the .jar file but you can always change the location by checking the *Choose save location* box, which will create a usual *save as* dialog option.

## What are the options?
Currently there are 6 + 1 options available: 6 generic and 1 that allow to create a nested objects.
You will find a shord description of each one below with their configuration parameters.
Pleas note that every element has to have a name (can be blank but what is the point then) which will represent the key value in json.

### JSON_OBJECT
A container that allows to create a nested json that consists of all available values.
<dl>
	<dt>count</dt>
	<dd>specifies format of the nested json. For 1 it will be a single object, for more than 1 it will be an array of objects.</dd>
</dl>
You can nest another json in already nested json. Use the JSON_OBJECT when adding new elements.

### JSON_BOOL
Adds option to generate boolean values.
<dl>
	<dt>only TRUE</dt>
	<dd>mark that if you want to generate only true values</dd>
	<dt>only FALSE</dt>
	<dd>mark that if you want to generate only false values</dd>
</dl>
**WARNING:** marking both *only TRUE* and *only FALSE* is an error.

### JSON_DATE
Adds option to generate date values.
<dl>
	<dt>lower bound</dt>
	<dd>all the generated dates will be not before the given value</dd>
	<dt>upper bound</dt>
	<dd>all the generated dates will be not after the given value</dd>
	<dt>output pattern</dt>
	<dd>specifies the format in which the date will be written. Has to follow rules of java.time.format.DateTimeFormatter</dd>
</dl>
If you want to generate dates on a specified day set *lower bound* equal to *upper bound*.

**WARNING:** *lower bound* must not be after *upper bound*.



