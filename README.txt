This open-source software is bound by an Apache 2 License.


*** Presentation ***

FilesToRepository is an open-source Java tool which allows to back-up in an intelligent manner files to a folder "repository".
It resolves conflicts automatically.

  * Supported OS

Linux, Mac OS X, Windows.

  * Limitations

No versioning. Newer files overwrite old files if the have the same name and are in the same folder.
The tool only accepts folders as input, files are not supported as input.
The copied files are created with default permissions, wich means on Windows it is not annoying, 
on Linux/Unix you may loose 'execute' right during copy. 
This file permissions limitation comes directly form an underlying Java limitation.

  * Use case

You gathered a whole folder hierarchy holding important files you need to back-up. You create a fresh new directory 
to hold your back-up on an external drive for instance. You use the command line tool passing as source-path the root folder 
of your to-back up file structure and as output-path the newly created directory (ending with the same directory name as 
the source). The tool will only (but with excellent performance) copy all the files and folders to-back-up to 
the external's drive directory.
Some time has passed, your to-back-up files structure has evolved, some new files/directories were added, some were removed. 
Here you have two options, use a fresh back-up folder or re-use the previous back-up folder (the one which was 
on an external drive). If you use a fresh back-up folder, you may waste space because perhaps some (or many) files 
have not changed since last back-up. Moreover, a full back-up is slow. The benefits of this tool are in doing 
an incremental back-up over the last back-up; please remember the No-Versioning limitation (see Limitations paragraph). 
If you choose to merge over the last back-up, only new files will be copied/overwritten, doing a much faster second back-up.

  * The "prune" command, WARNING!

Be extremely careful with --prune, if you don't use it properly, it can delete files in the master repository!
"Prune" is used to clean up leftover files in the "slave" repository. "Master repository" refers to the main repository, 
the "slave" is the copy. Over time, files/folders in the master can be renamed. Previous copied files are still 
in the slave repository. "Prune" compares the master and the slave files and deletes the slave files which are no present 
in the master repository. So, be careful to not invert master and slave.

Your files reference is always the "master". You can loose files in the slave structure, and recover them from the master. 
But if you delete files in the master and the files were new (not copied with "merge"), no recover is possible 
(without "undelete software" use).

  * Commands and where to run FTR jar file?

In "script" folder you have FTR launch examples. The launch commands have to executed at the root level of the project 
(where "build", "lib", "resources" etc folders are). Otherwise, the ASCII-Art files won't be found by the loader.

  * Supported Media

Read input: any mounted media on your system which is readable e.g. internal disk drive, external disk drive, 
CD/DVD (not tested so far but should be fine), flash memory stick (not tested so far but should be fine)

Write output: any mounted media on your system wich is writable through basic system operations e.g. internal disk drive, 
external disk drive. Flash memory stick can work but it is not recommended for back-up (they are not reliable enough for back-up).

  * Performance

The performance of this tool was tested in simple copy mode against the performance of visual file explorer 
under Linux Debian 40r4a and Windows XP Pro SP3.
-Linux Debian 40r4a: simple massive copy performance against Nautilus file explorer: about the same performance.
-Windows XP Pro SP3: simple massive copy performance against default Explorer : up to 35% faster than Explorer!

  * System requirements

Runtime & compilation:
-Environment: Java 1.4 or higher
-Operating system: any OS supporting Java 1.4 or higher e.g. Linux, Solaris, Windows XP/2000/Server 2003/Vista/7 ...


*** User Guide ***

* Help command

For a complete usage help feel free to try out --help command. The full help command  for this release is :
java -jar build/run-FTR_1.2.jar --help

* Version command

java -jar build/run-FTR_1.2.jar --version

* "Merge" and "Prune" commands

Please refer to the file commands-FTR_x.y.txt in "script" folder.


*** Trouble shooting

* You encounter class loading issues during command launch. Make sure you are using the correct Java version 
as specified in System requirements paragraph.

*** Bug report

If you encounter a problem you estimate as a 'bug', please make sure it is not an expected behaviour given 
the command line you entered. If you still estimate it is a 'malfunction' of the tool, please report it 
on the code platform, thank you.

____________________

    Author: mariuschristian
