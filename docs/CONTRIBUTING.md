# Contributing to LorenzNickel/java-fundamentals

:tada: First off, thanks for taking the time to contribute! :tada:

The following is a set of guidelines for contributing to [LorenzNickel/java-fundamentals](https://github.com/LorenzNickel/java-fundamentals) on GitHub. These are mostly guidelines, not rules. Use your best judgment, and feel free to propose changes to this document in a pull request.

## Pull Requests

Feel free to create pull requests covering additional (common) algorithms, datastructures, design pattern and problems (including a textual description) to this repository.

### Content

Please make sure to not violate any copyright rules when contributing to this repository.
In general, we are happy to receive any contributions covering new implementations or problem statements.
Regarding the structuring of your files it might be useful to check similar implementations in this repository first and adapt their style.

### Style Guide

This repository follows the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html). Feel free to download formatting presets for your IDE from [Google's GitHub repository](https://github.com/google/styleguide).
We have set up [checkstyle](https://github.com/LorenzNickel/java-fundamentals/blob/master/checkstyle.xml) to enforce them throughout this project. You can check locally if your changes comply with these guidelines by executing the maven goal `compile`. If the build of your Pull Request fails make sure to checkout the logs in the checks of your Pull Request to see what exactly does not comply with our guidelines. If you think that you are still complying with the guidelines, feel free to make corresponding changes to our [checkstyle file](https://github.com/LorenzNickel/java-fundamentals/blob/master/checkstyle.xml).