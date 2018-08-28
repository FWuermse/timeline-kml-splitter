class GLocation {

    private String date
    private String gps

    String getDate() {
        return date
    }

    void setDate(String date) {
        this.date = date
    }

    String getGps() {
        return gps
    }

    void setGps(String gps) {
        this.gps = gps
    }
}

static void main(String[] args) {
    println ("Please set the path and filename of your kml file. For instance: C:\\Users\\Downloads\\mymap.kml")
    File file = new File(System.in.newReader().readLine())
    println("Locations from which year do you want to extract?")
    String year = System.in.newReader().readLine()
    println("Locations from which month do you want to extract?")
    String month = System.in.newReader().readLine()

    createFile(year, month, createFileContent(createFileContentBody(getLocationList(file, year, month))))
}

List<GLocation> getLocationList(File file, String year, String month) {
    GLocation gLocation = new GLocation()
    List<GLocation> locationList = new LinkedList<>()

    for (String line : file) {
        if (line.contains("<when>")) {
            gLocation.date = line
        } else if (line.contains("<gx:coord>")) {
            gLocation.gps = line
            if (filterLocation(gLocation, year, month)) {
                locationList.add(gLocation)
            }
            gLocation = new GLocation()
        }
    }
    println("${locationList.size()} Locations have been added")
    return locationList
}

static def filterLocation(GLocation myGLocation, String year, String month) {
    if (year != "") {
        if (month != "") {
            if (month.length() == 1)
                month = "0$month"
            return myGLocation.date.contains("<when>$year-$month")
        } else {
            return myGLocation.date.contains("<when>$year")
        }
    } else {
        return true
    }
}

static String createFileContent(String fileBody) {
    def fileHeader = "<?xml version='1.0' encoding='UTF-8'?>\n" +
            "<kml xmlns='http://www.opengis.net/kml/2.2' xmlns:gx='http://www.google.com/kml/ext/2.2'>\n" +
            "\t<Document>\n" +
            "\t\t<Placemark>\n" +
            "\t\t\t<open>1</open>\n" +
            "\t\t\t<gx:Track>\n" +
            "\t\t\t\t<altitudeMode>clampToGround</altitudeMode>\n"
    def fileFooter = "\t\t\t</gx:Track>\n" +
            "\t\t</Placemark>\n" +
            "\t</Document>\n" +
            "</kml>"
    return "$fileHeader$fileBody$fileFooter"
}

static String createFileContentBody(List<GLocation> locationList) {
    StringBuilder stringBuilder = new StringBuilder()

    for (GLocation location : locationList) {
        stringBuilder.append(location.date)
        stringBuilder.append("\n")
        stringBuilder.append(location.gps)
        stringBuilder.append("\n")
    }
    return stringBuilder.toString()
}

def createFile(String year, String month, String fileContent) {
    def fileName = "LocationHistory_$year-$month-all.kml"
    new File("locations").mkdir()
    def inputFile = new File("${System.getProperty("user.dir")}\\locations\\$fileName\\")
    inputFile.write(fileContent)
    println"File has been successfully created as: ${System.getProperty("user.dir")}\\locations\\$fileName"
}
