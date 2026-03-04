# Fix ForgeTier -> SimpleTier
$files = @(
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\item\ModTiers.java',
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\mineral\tools\MineralToolTier.java'
)
foreach ($f in $files) {
  (Get-Content $f -Raw) -replace 'ForgeTier', 'SimpleTier' | Set-Content $f -Encoding UTF8
  Write-Host "Fixed ForgeTier: $f"
}

# Fix TickEvent -> PlayerTickEvent.Post (event files)
$tickFiles = @(
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\event\ActionBasedConsumptionHandler.java',
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\event\LeopardGeckoArmorEventHandler.java',
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\event\LunarClawEventHandler.java',
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\event\VitalDebuffHandler.java',
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\event\VitalDepletionHandler.java'
)
foreach ($f in $tickFiles) {
  $c = Get-Content $f -Raw
  $c = $c -replace 'import net\.neoforged\.neoforge\.event\.TickEvent;', 'import net.neoforged.neoforge.event.tick.PlayerTickEvent;'
  $c = $c -replace 'public static void onPlayerTick\(TickEvent\.PlayerTickEvent event\)', 'public static void onPlayerTick(PlayerTickEvent.Post event)'
  # Remove phase check + fix player access
  $c = $c -replace '(?s)        if \(event\.phase != TickEvent\.Phase\.END\) \{.+?        \}\r?\n\r?\n        Player player = event\.player;', '        Player player = event.getEntity();'
  $c = $c -replace 'event\.player', 'event.getEntity()'
  $c = $c -replace 'import net\.neoforged\.fml\.common\.Mod;\r?\n', ''
  Set-Content $f $c -Encoding UTF8
  Write-Host "Fixed TickEvent: $f"
}

# Fix LivingAttackEvent -> LivingIncomingDamageEvent
$attackFiles = @(
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\event\TuataraArmorEventHandler.java',
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\mineral\MineralEffectHandler.java',
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\event\ActionBasedConsumptionHandler.java'
)
foreach ($f in $attackFiles) {
  $c = Get-Content $f -Raw
  $c = $c -replace 'import net\.neoforged\.neoforge\.event\.entity\.living\.LivingAttackEvent;', 'import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;'
  $c = $c -replace 'LivingAttackEvent event', 'LivingIncomingDamageEvent event'
  Set-Content $f $c -Encoding UTF8
  Write-Host "Fixed LivingAttackEvent: $f"
}

# Fix AgeableaterAnimal - remove MobType
$f = 'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\entity\custom\base\AgeableaterAnimal.java'
$c = Get-Content $f -Raw
$c = $c -replace 'import net\.minecraft\.world\.entity\.MobType;\r?\n', ''
$c = $c -replace '    public MobType getMobType\(\) \{\r?\n        return MobType\.WATER;\r?\n    \}\r?\n\r?\n', ''
Set-Content $f $c -Encoding UTF8
Write-Host "Fixed AgeableaterAnimal"

# Fix Vanishable removal in CaptureNet and MantisGrab
$vanishFiles = @(
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\item\custom\weapon\CaptureNet.java',
  'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\item\custom\weapon\MantisGrab.java'
)
foreach ($f in $vanishFiles) {
  $c = Get-Content $f -Raw
  $c = $c -replace 'import net\.minecraft\.world\.item\.Vanishable;\r?\n', ''
  $c = $c -replace ' implements Vanishable', ''
  Set-Content $f $c -Encoding UTF8
  Write-Host "Fixed Vanishable: $f"
}

# Fix SoilTooltipHandler - RenderGuiOverlayEvent -> RenderGuiLayerEvent
$f = 'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\ferm\SoilTooltipHandler.java'
$c = Get-Content $f -Raw
$c = $c -replace 'import net\.neoforged\.neoforge\.client\.event\.RenderGuiOverlayEvent;', 'import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;'
$c = $c -replace 'public static void onRenderGameOverlay\(RenderGuiOverlayEvent\.Post event\)', 'public static void onRenderGameOverlay(RenderGuiLayerEvent.Post event)'
$c = $c -replace 'import net\.neoforged\.fml\.common\.Mod;\r?\n', ''
Set-Content $f $c -Encoding UTF8
Write-Host "Fixed SoilTooltipHandler"

# Fix ModEvents.java - LivingHurtEvent -> LivingDamageEvent.Pre, LivingEvent.LivingTickEvent -> EntityTickEvent.Post
$f = 'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\event\ModEvents.java'
$c = Get-Content $f -Raw
$c = $c -replace 'import net\.neoforged\.neoforge\.event\.entity\.living\.LivingHurtEvent;', 'import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;'
$c = $c -replace 'import net\.neoforged\.neoforge\.event\.entity\.living\.LivingEvent;', "import net.neoforged.neoforge.event.entity.living.LivingEvent;`nimport net.neoforged.neoforge.event.tick.EntityTickEvent;"
$c = $c -replace 'public static void dreamer\(LivingHurtEvent event\)\{', 'public static void dreamer(LivingDamageEvent.Pre event){'
$c = $c -replace 'public static void axo\(LivingEvent\.LivingTickEvent event\)', 'public static void axo(EntityTickEvent.Post event)'
$c = $c -replace '        Level level = event\.getEntity\(\)\.level\(\);\r?\n           if\(event\.getEntity\(\) instanceof Axolotl axolotl\)\{', "        if (!(event.getEntity() instanceof Axolotl axolotl)) return;`n           Level level = axolotl.level();`n           {"
$c = $c -replace 'import net\.neoforged\.fml\.common\.Mod;\r?\n', ''
Set-Content $f $c -Encoding UTF8
Write-Host "Fixed ModEvents.java"

# Fix ClientEvents.java - TickEvent.ClientTickEvent
$f = 'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\event\ClientEvents.java'
$c = Get-Content $f -Raw
$c = $c -replace 'import net\.neoforged\.neoforge\.event\.TickEvent;', 'import net.neoforged.neoforge.client.event.ClientTickEvent;'
$c = $c -replace '(?s)public static void onClientTickRhino\(TickEvent\.ClientTickEvent event\) \{.+?handleRhinoInput\(\);.+?\}', "public static void onClientTickRhino(ClientTickEvent.Post event) {`n            handleRhinoInput();`n        }"
$c = $c -replace 'import net\.neoforged\.neoforge\.client\.gui\.overlay\.VanillaGuiOverlay;', 'import net.neoforged.neoforge.client.gui.VanillaGuiLayers;'
$c = $c -replace 'VanillaGuiOverlay\.HOTBAR\.id\(\)', 'VanillaGuiLayers.HOTBAR_ELEMENTS'
$c = $c -replace 'import net\.neoforged\.fml\.common\.Mod;\r?\n', ''
Set-Content $f $c -Encoding UTF8
Write-Host "Fixed ClientEvents.java"

# Fix SoilFallowHandler.java - LevelTickEvent
$f = 'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\ferm\SoilFallowHandler.java'
$c = Get-Content $f -Raw
$c = $c -replace 'import net\.neoforged\.neoforge\.event\.TickEvent;', 'import net.neoforged.neoforge.event.tick.LevelTickEvent;'
$c = $c -replace 'public static void onLevelTick\(TickEvent\.LevelTickEvent event\)', 'public static void onLevelTick(LevelTickEvent.Post event)'
$c = $c -replace '(?s)        if \(event\.phase != TickEvent\.Phase\.END\) return;\r?\n        if \(!\(event\.level instanceof ServerLevel serverLevel\)\) return;', '        if (!(event.getLevel() instanceof ServerLevel serverLevel)) return;'
$c = $c -replace 'import net\.neoforged\.fml\.common\.Mod;\r?\n', ''
Set-Content $f $c -Encoding UTF8
Write-Host "Fixed SoilFallowHandler.java"

# Fix SparrowEntity.java - ForgeEventFactory
$f = 'E:\mod\PochiMod\src\main\java\net\pochi\pochimod\entity\custom\SparrowEntity.java'
$c = Get-Content $f -Raw
$c = $c -replace 'import net\.neoforged\.neoforge\.event\.ForgeEventFactory;', "import net.neoforged.neoforge.common.NeoForge;`nimport net.neoforged.neoforge.event.entity.living.AnimalTameEvent;`nimport net.neoforged.neoforge.event.entity.EntityMobGriefingEvent;"
$c = $c -replace '!ForgeEventFactory\.onAnimalTame\(this, pPlayer\)', '!NeoForge.EVENT_BUS.post(new AnimalTameEvent(this, pPlayer)).isCanceled()'
$c = $c -replace 'net\.neoforged\.neoforge\.event\.ForgeEventFactory\.getMobGriefingEvent\(SparrowEntity\.this\.level\(\), SparrowEntity\.this\)', '!NeoForge.EVENT_BUS.post(new EntityMobGriefingEvent(SparrowEntity.this)).isCanceled()'
Set-Content $f $c -Encoding UTF8
Write-Host "Fixed SparrowEntity.java"

Write-Host "`nScript complete!"
