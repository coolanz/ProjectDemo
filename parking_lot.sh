#!/bin/bash

chmod +x parking_lot.sh

D:/Softwares/Java/java_8/bin/javac com/*/*/*.java

jar -cvfm parkme.jar MANIFEST.txt -cp com *.class

java -jar parkme.jar $1