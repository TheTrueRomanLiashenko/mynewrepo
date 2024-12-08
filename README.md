# Shortener
__Hello there!__

This is a special Shortener program, which allows you to shorten any Strings you want!

To do it proper way, we have _Helper_ class with method _generateRandomString()_ which uses _SecureRandom_, so we get a unique String instance every time we invoke this method.
But we also need a way to storage shortened Strings. So there are a lot of Storage Strategies in the package __strategy__, which provides us different possibilities to do it.

In the _Main_ class I did several manual tests to understand which one strategy is better, and then I decided to do it using __JUnit__ — a great framework for module testing!
You can see my tests in package _tests_. The first one is to make sure that everything works as we want it to work, and in the second one we test HashMapStorageStrategy's and
HashBiMapStorageStrategy's time spent on getting a set of IDs and a set of Strings. 

___NOTE:___ To run this project, you need next libraries added:

_google.guava_

_junit_

_apache commons collections4_
