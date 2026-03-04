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

MARKER = 'new ResourceLocation('
MLEN = len(MARKER)

for filepath in java_files:
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    if MARKER not in content:
        continue

    original = content

    result = []
    i = 0
    while i < len(content):
        if content[i:i+MLEN] == MARKER:
            # Find matching close paren
            start = i + MLEN  # position after 'new ResourceLocation('
            depth = 1
            j = start
            while j < len(content) and depth > 0:
                if content[j] == '(':
                    depth += 1
                elif content[j] == ')':
                    depth -= 1
                j += 1
            inner = content[start:j-1]
            # Check if it has a top-level comma (two args)
            depth2 = 0
            comma_pos = -1
            for k, ch in enumerate(inner):
                if ch in '([':
                    depth2 += 1
                elif ch in ')]':
                    depth2 -= 1
                elif ch == ',' and depth2 == 0:
                    comma_pos = k
                    break
            if comma_pos >= 0:
                arg1 = inner[:comma_pos].strip()
                arg2 = inner[comma_pos+1:].strip()
                result.append(f'ResourceLocation.fromNamespaceAndPath({arg1}, {arg2})')
            else:
                # Single arg
                arg = inner.strip()
                if arg.startswith('"') and arg.endswith('"'):
                    # String literal - check if it has a colon
                    inner_str = arg[1:-1]  # remove quotes
                    if ':' in inner_str:
                        result.append(f'ResourceLocation.parse({arg})')
                    else:
                        result.append(f'ResourceLocation.withDefaultNamespace({arg})')
                else:
                    # Variable or complex expression - leave as-is (can't determine namespace)
                    result.append(f'new ResourceLocation({arg})')
            i = j
        else:
            result.append(content[i])
            i += 1

    content = ''.join(result)
    count = original.count(MARKER) - content.count(MARKER)
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
    count = content.count(MARKER)
    if count > 0:
        remaining_files.append((filepath.replace(src_dir, ''), count))

if remaining_files:
    print(f"\nRemaining new ResourceLocation( ({sum(c for _,c in remaining_files)} in {len(remaining_files)} files):")
    for f, c in sorted(remaining_files, key=lambda x: -x[1])[:20]:
        print(f"  {c}x {f}")
else:
    print("\nAll new ResourceLocation() usages fixed!")
