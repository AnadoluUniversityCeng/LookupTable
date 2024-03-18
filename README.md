# LookupTable
The tablelookup function for the the "Z-Chart &amp; Loss Function" table 

The task is the implementation of the table lookup of the "Z-Chart & Loss Function" values.
This is a part of the project. There is a table in the book, (for the tab seperated values *.tsv file [click here](https://github.com/AnadoluUniversityCeng/LookupTable/blob/main/zChart.tsv) where we look for a value of a function. Let's call it f(x)=y. The input is the x, the output will be the y.

The ultimate goal is to retrieve/find/calculate the F(Z) and L(Z) values for a given Z value. For example if the input is -3 (i.e. Z=-3) the output will be F(Z)=0.0013 and L(Z)=3.000. If the exact Z value of the input does not exist in the table, we will use the closest Z value in the table. For example if the input is -2.95 (i.e. Z=-2.95) the output will be F(Z=-2.96)=0.0053 and L(Z=2.96)=2.960. Note that the is no entry for Z=-2.95 in the table, thus we will use the closest existing value to -2.95, which is -2.96F(Z) and L(Z) values. In case of a tie; either, you can use/return an arithmetic mean (i.e. average) of F(Z) and L(Z) values, Or break the tie randomly.

Note that Z values in the "Z-Chart & Loss Function" table or in any lookup table must be strictly monotonic, either increasing or decreasing.

Came up with your own implementation strategy of the task. Use an appropriate data structure (may be a TreeMap?) and algorithm for finding the closest Z value.  Once you finish describe the methodology you adopted to solve the problem in the comments section. ChatGPT vs all resources can be used.



The function returns an output value, in the units specified for fd, by looking up or estimating table values based on the input values:

When inputs Z
The tablelookup function...
Match the values in the input data set	Outputs the corresponding table value
Do not match the values in the input data sets, but are within range	Interpolates appropriate table values, using the method specified as the nearest
Do not match the values in the input data sets, and are out of range	Generates an error when the input value is outside the range specified in the lookup table.
Work Smarter, Not Harder: Before jumping into the actual implementation (may be there is a smarter way to solve the problem than a table lookup?) think and research 10-15 minutes about how to retrieve/find/calculate the F(Z) and L(Z) values for a given Z value [between -3.0 and +3.0], where:

F(Z) is the probability that a variable from a standard normal distribution will be less than or equal to Z
L(Z) is the standard loss function


Throw an exception (of appropriate type) when the input value is outside the range [-3.0 ... 3.0] specified in the lookup table. Some background on the Lookup Table Basics:

https://en.wikipedia.org/wiki/Lookup_table (Harici bir siteye bağlantılar.)

https://developer.nvidia.com/gpugems/gpugems2/part-iii-high-quality-rendering/chapter-24-using-lookup-tables-accelerate-color (Harici bir siteye bağlantılar.)

https://www.mathworks.com/help/simscape/lang/tablelookup.html (Harici bir siteye bağlantılar.)

In computer science, a lookup table is an array that replaces runtime computation with a simpler array indexing operation, in a process termed as direct addressing.

Lookup tables (LUTs) are an excellent technique for optimizing the evaluation of functions that are expensive to compute and inexpensive to cache. By precomputing the evaluation of a function over a domain of common inputs, expensive runtime operations can be replaced with inexpensive table lookups. If the table lookups can be performed faster than computing the results from scratch (or if the function is repeatedly queried at the same input), then the use of a lookup table will yield significant performance gains.  For data requests that fall between the table's samples, an interpolation algorithm can generate reasonable approximations by averaging nearby samples.