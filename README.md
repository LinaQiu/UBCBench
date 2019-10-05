# UBCBench
UBCBench contains all benchmarks we designed for confirming our hypothesized findings while analyzing the three well-known static taint analysis tools, FlowDroid+IccTA, Amandroid, and DroidSafe. 

# UBCBench Results - DroidIccSourceSinks.xlsx
We ran all benchmarks with the three aforementioned tools, using the same tool configurations and [sources and sinks list (DroidIccSourceSinks)](https://resess.github.io/PaperAppendices/ISSTA2018.html#ExperimentalSetup) we described in our ISSTA paper. We analyzed all tool analysis results and provided our analysis results, along with the ground truth in the excel file called "UBCBench Results - DroidIccSourceSinks.xlsx".

# Benchmark Names and Mappings
- UBC1: FlowInsensitivity
- UBC2: ReflectiveSource
- UBC3: GetClass
- UBC4: MethodWithNonPrimitive
- UBC5: PassTaintToReflection
- UBC6: SquareRoot
- UBC7: RandomNumber
- UBC8: AccessPrivateField
- UBC9: StaticField
- UBC10: ArrayAndElements
- UBC11: ValueOfMethod
- UBC12: ViewCasting
- UBC13: BroadcastReceiverOnReceive
- UBC14: SetGetHint
- UBC15: CallbacksInClass
- UBC16: ICCInXMLCallback
- UBC17: ReturnIntentFilter
- UBC18: ImplicitIntentNoAction
- UBC19: OnServiceConnected
- UBC20: HardCodedLocationTest
- UBC21: CallbackRegisterCallback
- UBC22: TaintNotDestroy
- UBC23: ServiceIssue
- UBC24: FragmentLifecycle
- UBC25: LocationMethods
- UBC26: ConservativeModel
- UBC27: ExtendIDDGForSinkApis
- UBC28: CallbacksIntentHandling
- UBC29: MultiEntryIssue
- UBC30: LocalBroadcast
- UBC31: PassSourceSink
- UBC32: ButterKnife & ButterKnifeReflection
- UBC33: PackagePrefix
- UBC34: WrongIccLink
- UBC35: IteratorPolymophism
- UBC36: ExtendIDDGForSinkApisLog