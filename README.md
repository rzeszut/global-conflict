LibGDX project setup
====================

Setup IDEA project
------------------

Get this template

    git clone https://github.com/libgdx/libgdx-gradle-template

Run idea build:

    ./gradlew idea

Open \*.ipr file in IDEA.

Any other setup according to [README](https://github.com/libgdx/libgdx-gradle-template/blob/master/README.md).

Android debug compilation errors
--------------------------------

If errors like that occur:

    Execution failed for task ':android:mergeDebugResources'.
    > /home/mateusz/prog/libgdx-gradle-template/android/res/drawable-mdpi/ic_launcher.png:
    Error: Cannot run program "/home/mateusz/bin/android-sdk-linux/build-tools/19.0.1/aapt": error=2, Nie ma takiego pliku ani katalogu

Install those two libs:

    sudo apt-get install lib32stdc++6 lib32z1
