# Name

FileTypeAnalyzer

# Installation

Requires JDK version 11 or later

# Description

The FileTypeAnalyzer program determines the types of files

The FileTypeAnalyzer program accepts a patterns file and a path to a file or directory with files.

The program looks for all patterns in each file and thus determines the file type. 
Patterns use a simple prioritization scheme. When finding several patterns in a file, the program gives preference to the pattern with the highest priority.

# Usage example

java Main test_files patterns.db  <br/>
test_filesdoc_0.doc: MS Office Word 2003  <br/>
doc_1.ppt: MS Office PowerPoint 2003  <br/>
file.zip: Zip archive  <br/>

