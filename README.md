# UBCBench
UBCBench contains all benchmarks we designed for confirming our hypothesized findings while analyzing the three well-known static taint analysis tools, FlowDroid+IccTA, Amandroid, and DroidSafe. 

*If you want to find the version of UBCBench used in our ISSTA'18 paper, please check [here](https://github.com/LinaQiu/UBCBench/tree/ISSTA).*

# UBCBench Results.xlsx
We ran all benchmarks with the three aforementioned tools, using the same tool configurations and [sources and sinks list](https://resess.github.io/PaperAppendices/StaticTaint/benchmark/#sources-and-sinks) we described in our Empirical Software Engineering paper. We analyzed all tool analysis results and provided our analysis results, along with the ground truth in the excel file called "UBCBench Results.xlsx".

# UBCBench Benchmark Names and Mappings
- UBC1: ReflectiveSource
- UBC2: ViewCasting
- UBC3: ConservativeModel
- UBC4: HardCodedLocationTest
- UBC5: CallbacksIntentHandling
- UBC6: GetClass

# Benchmarks for testing sensitivities and untangling purpose
We created five benchmarks under folder [Sensitivity](/android-studio-project/Sensitivity) for testing what kinds of sensitivities are supported by a tool, and nine benchmarks under folder [Untangling](/android-studio-project/Untangling) to untangling original benchmark apps.