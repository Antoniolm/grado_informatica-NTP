

val files= (new java.io.File(".")).listFiles()

for(file <- files
      if file.isFile) yield file

for{
  x<-1 to 2
  y<-1 to 3
}yield (x,y)

