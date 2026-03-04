import re

filepath = r'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\block\ModBlocks.java'

with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

original = content

# Fix 1: GlassBlock -> TransparentBlock
content = content.replace('new GlassBlock(', 'new TransparentBlock(')

# Fix 2: StairBlock - replace () -> ModBlocks.XXX.get().defaultBlockState() with direct BlockState
content = re.sub(
    r'new StairBlock\(\(\) -> ModBlocks\.\w+\.get\(\)\.defaultBlockState\(\),',
    'new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(),',
    content
)

# Fix 3: FenceGateBlock - swap (Properties, WoodType) -> (WoodType, Properties)
# Pattern: new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.XXX).strength(2.0F,3.0F),\n    WoodType.OAK)
content = re.sub(
    r'new FenceGateBlock\((BlockBehaviour\.Properties\.ofFullCopy\(Blocks\.[A-Z_]+\)\.strength\(2\.0F,3\.0F\)),\s*\n\s*WoodType\.OAK\)',
    r'new FenceGateBlock(WoodType.OAK, \1)',
    content
)

# Fix 4: PressurePlateBlock - (Sensitivity, Properties, BlockSetType) -> (BlockSetType, Properties)
# Pattern: new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,\n    Props.noCollission()\n        .strength(0.5F),\n    BlockSetType.OAK)
content = re.sub(
    r'new PressurePlateBlock\(PressurePlateBlock\.Sensitivity\.EVERYTHING,\s*\n(\s*)(BlockBehaviour\.Properties\.ofFullCopy\(Blocks\.[A-Z_]+\)\.noCollission\(\))\s*\n\s*(\.strength\(0\.5F\)),\s*\n\s*BlockSetType\.OAK\)',
    r'new PressurePlateBlock(BlockSetType.OAK, \2\3)',
    content
)

# Fix 5: DoorBlock - (Properties, BlockSetType) -> (BlockSetType, Properties)
# Pattern: new DoorBlock(Props.strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never),\n    BlockSetType.OAK)
content = re.sub(
    r'new DoorBlock\((BlockBehaviour\.Properties\.ofFullCopy\(Blocks\.[A-Z_]+\)\.strength\(3\.0F\)\.noOcclusion\(\)\.isValidSpawn\(ModBlocks::never\)),\s*\n\s*BlockSetType\.OAK\)',
    r'new DoorBlock(BlockSetType.OAK, \1)',
    content
)

# Fix 6: TrapDoorBlock - (Properties, BlockSetType) -> (BlockSetType, Properties)
# The Properties span 2 lines:
# Pattern A: BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)\n    .strength(3.0F).noOcclusion().isValidSpawn(ModBlocks::never), BlockSetType.OAK
# Pattern B: BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)\n    .strength(3.0F).sound(SoundType.WOOD).noOcclusion().isValidSpawn(ModBlocks::never), BlockSetType.OAK
content = re.sub(
    r'new TrapDoorBlock\((BlockBehaviour\.Properties\.ofFullCopy\(Blocks\.[A-Z_]+\))\s*\n(\s*)(\.strength\(3\.0F\)[^,]+?), BlockSetType\.OAK\)',
    r'new TrapDoorBlock(BlockSetType.OAK, \1\n\2\3)',
    content
)

count1 = original.count('new GlassBlock(')
count2 = len(re.findall(r'new StairBlock\(\(\) -> ModBlocks', original))
count3 = len(re.findall(r'new FenceGateBlock\(BlockBehaviour', original))
count4 = len(re.findall(r'new PressurePlateBlock\(PressurePlateBlock', original))
count5 = len(re.findall(r'new DoorBlock\(BlockBehaviour', original))
count6 = len(re.findall(r'new TrapDoorBlock\(BlockBehaviour', original))

print(f"GlassBlock replacements: {count1}")
print(f"StairBlock supplier replacements: {count2}")
print(f"FenceGateBlock arg-swap: {count3}")
print(f"PressurePlateBlock Sensitivity removal: {count4}")
print(f"DoorBlock arg-swap: {count5}")
print(f"TrapDoorBlock arg-swap: {count6}")

# Verify remaining issues
remaining_glass = content.count('new GlassBlock(')
remaining_stair_supplier = len(re.findall(r'new StairBlock\(\(\) -> ModBlocks', content))
remaining_fence = len(re.findall(r'new FenceGateBlock\(BlockBehaviour', content))
remaining_pressure = len(re.findall(r'PressurePlateBlock\.Sensitivity', content))
remaining_door = len(re.findall(r'new DoorBlock\(BlockBehaviour', content))
remaining_trapdoor = len(re.findall(r'new TrapDoorBlock\(BlockBehaviour', content))

print(f"\nRemaining GlassBlock: {remaining_glass}")
print(f"Remaining StairBlock suppliers: {remaining_stair_supplier}")
print(f"Remaining FenceGateBlock(Props,...): {remaining_fence}")
print(f"Remaining PressurePlate Sensitivity: {remaining_pressure}")
print(f"Remaining DoorBlock(Props,...): {remaining_door}")
print(f"Remaining TrapDoorBlock(Props,...): {remaining_trapdoor}")

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)

print("\nFile updated.")
