import re

filepath = r'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\block\ModBlocks.java'

with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

# Fix remaining PressurePlateBlock with .sound(SoundType.WOOD) at end
# Pattern: new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,\n    Props.noCollission()\n        .strength(0.5F).sound(SoundType.WOOD),\n    BlockSetType.OAK)
content = re.sub(
    r'new PressurePlateBlock\(PressurePlateBlock\.Sensitivity\.EVERYTHING,\s*\n(\s*)(BlockBehaviour\.Properties\.ofFullCopy\(Blocks\.[A-Z_]+\)\.noCollission\(\))\s*\n\s*(\.strength\(0\.5F\)\.sound\(SoundType\.WOOD\)),\s*\n\s*BlockSetType\.OAK\)',
    r'new PressurePlateBlock(BlockSetType.OAK, \2\3)',
    content
)

remaining = len(re.findall(r'PressurePlateBlock\.Sensitivity', content))
print(f"Remaining PressurePlate Sensitivity: {remaining}")

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)

print("Done.")
