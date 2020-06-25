# UBCBench
UBCBench contains all benchmarks we designed for confirming our hypothesized findings while analyzing the three well-known static taint analysis tools, FlowDroid+IccTA, Amandroid, and DroidSafe. 

# UBCBench Results - DroidIccSourceSinks.xlsx
We ran all benchmarks with the three aforementioned tools, using the same tool configurations and [sources and sinks list](https://resess.github.io/PaperAppendices/StaticTaint/benchmark/#sources-and-sinks) we described in our Empirical Software Engineering paper. We analyzed all tool analysis results and provided our analysis results, along with the ground truth in the excel file called "UBCBench Results.xlsx".

# Benchmark Names and Mappings
- U-DB2.12: ConservativeModel1-3
- U-DB4.15: HardCodedLocationTest
- U-DB4.16: CallbacksIntentHandling
- U-DB4.17: SetContentView
- U-DB4.18: CallbacksInFragment
- U-DB7.24: ViewCasting
- U-DB7.25: ContextSensitivityDepth
- U-DB7.26: DuplicatedFlows
- U-DB10.6: ReflectiveSource
- U-DB10.7: GetClass
- U-DB10.8: GetConstructor