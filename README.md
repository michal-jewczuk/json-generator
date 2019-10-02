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
#### count
Specifies format of the nested json. For 1 it will be a single object, for more than 1 it will be an array of objects.
You can nest another json in already nested json. Use the JSON_OBJECT when adding new elements.