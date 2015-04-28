# Http-Unit-Test
Using Http Unit Test

## Solution In Encoding Error
I have to solve this Chinese word encoding not good, from two main aspects, firstly,
I have to make the `HTML` content we get through `Http Unit` have the correct
encoding charset `UTF-8`, so I have to change http unit settings, by using
`HttpUnitOptions.setDefaultCharacterSet("UTF-8");` to change the charset, hense we
can get the right charset `UTF-8` from Jave Console. Secondly, we must make that the
file we write in has correct charset enough, becasue we use `Windows 7` and it's
default charset is `GBK`, and linux system's default charset is `UTF-8`, so I have to
do one thing that when we generte a file using `FileOutputStream`, and I write 'String'
into it, we I call 'String.getBytes()', I must specify the charset `UT-8`, so I just call
`String.getBytes("UTF-8")` to assign the charset, and then the file I get on Windows Platform
is encoded by `UTF-8`

## Links
    - java.io写文件在linux中出现中文乱码之解决:http://yangfei520.blog.51cto.com/1041581/551057
    - 做页面抓取，本地文件都可以抓，但是一抓别的就报错:http://bbs.csdn.net/topics/390719480
    - [Config] jar(Http Unit Dependency) downloads:http://www.filewatcher.com/b/ftp/ftp2.slac.stanford.edu/glast.u05/tog/Together6.0.1/lib-0.html
    - [Config] Understanding HttpUnit's dependencies:http://httpunit.sourceforge.net/doc/manual/installing.html
