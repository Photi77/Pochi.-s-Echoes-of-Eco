import re
import os

src_dir = r'E:\mod\PochiMod\src'

java_files = []
for root, dirs, files in os.walk(src_dir):
    for f in files:
        if f.endswith('.java'):
            java_files.append(os.path.join(root, f))

total_fixed = 0
files_fixed = 0

for filepath in java_files:
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    original = content

    # Fix: new ResourceLocation(EXPR, "path") -> ResourceLocation.fromNamespaceAndPath(EXPR, "path")
    # Matches: new ResourceLocation( + any expression + , + quoted string + )
    # where EXPR is not a quoted string (already handled by first script)
    # EXPR pattern: identifier/constant like PochiMod.MOD_ID, or variable
    content = re.sub(
        r'new ResourceLocation\(([^")(,][^)(,]*),\s*("(?:[^"\\]|\\.)*")\)',
        r'ResourceLocation.fromNamespaceAndPath(\1, \2)',
        content
    )

    count = original.count('new ResourceLocation(') - content.count('new ResourceLocation(')
    if count > 0:
        total_fixed += count
        files_fixed += 1
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)

print(f"Fixed {total_fixed} occurrences across {files_fixed} files")

# Report remaining
remaining_files = []
for filepath in java_files:
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    count = content.count('new ResourceLocation(')
    if count > 0:
        remaining_files.append((filepath.replace(src_dir, ''), count))

if remaining_files:
    print(f"\nRemaining new ResourceLocation( ({sum(c for _,c in remaining_files)} in {len(remaining_files)} files):")
    for f, c in sorted(remaining_files, key=lambda x: -x[1])[:20]:
        print(f"  {c}x {f}")
else:
    print("\nNo remaining new ResourceLocation() usages!")
