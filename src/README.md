# A simple script for splitting KML files

If you have a large amount of Locations in a KML file you can split it up into different smaller ones with this tool by selecting a time span.

## Use cases
### Google timeline
This google service constantly tracks your location data and shows you how you travelled on a specific day. However it only ever shows you a single day.
However they offer you to export all (and only all) your location data via the `Download a copy of all your data` button or go to [Googles Export settings](https://takeout.google.com/settings/takeout) and select only the `Location History` as **KLM** format.

Then you can simply insert select it with this tool and choos which years / months you want in your new KML file. Then just simply import it again with google MyMaps or any other tool that captures locations.

### Travel maps
If you travelled to a specific place and want to review where you went and which route you took. As described you can use the date from [Googles Export settings](https://takeout.google.com/settings/takeout) in case you are using google and select the month you travelled in. Then just import it into MyMaps or Maps.me to get a travel map from your last vacation.
## Usage
In order to use it you need to have both installed,  If you do so just run the batch file and enter the file name of your KML as well as the time span you want to have in your new file.

* Install [Java](https://www.oracle.com/technetwork/java/javase/downloads/index.html) and [Groovy](http://groovy-lang.org/download.html).
* Download this Github project
* Double click the Start.bat
* Select your File
* Choose a time span
