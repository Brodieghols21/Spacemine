//GiantLuigi4
package anticookiestudios.spacemine;

import net.gigabit101.shrink.api.ShrinkAPI;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.fml.ModList;
import net.teamfruit.gulliver.GulliverSize;
import net.teamfruit.gulliver.attributes.Attributes;
import net.threetag.threecore.capability.CapabilitySizeChanging;
import virtuoel.pehkui.Pehkui;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class ResizingUtils {
	private static final UUID uuidHeight = UUID.fromString("5440b01a-974f-4495-bb9a-c7c87424bca4");
	private static final UUID uuidWidth = UUID.fromString("3949d2ed-b6cc-4330-9c13-98777f48ea51");

	public static void resize(Entity entity, float newSize) {
		//TODO: chiseled me integration
		if (ModList.get().isLoaded("pehkui")) {
			if (ModList.get().isLoaded("threecore")) {
				entity.getCapability(CapabilitySizeChanging.SIZE_CHANGING).ifPresent((cap) -> {
					if (cap.getSizeChangeType().equals(SpacemineResizeType.SPACEMINE_CHANGE_TYPE.get())) {
						cap.setSizeDirectly(SpacemineResizeType.SPACEMINE_CHANGE_TYPE.get(), 1);
					}
				});
			}
			if (ModList.get().isLoaded("gulliver") && entity instanceof LivingEntity) {
				GulliverSize.changeSize((LivingEntity) entity, 1);
			}
			Spacemine.SpacemineScaleType.get().getScaleData(entity).setTargetScale(newSize);
		} else if (ModList.get().isLoaded("gulliver") && entity instanceof LivingEntity) {
			if (ModList.get().isLoaded("threecore")) {
				entity.getCapability(CapabilitySizeChanging.SIZE_CHANGING).ifPresent((cap) -> {
					if (cap.getSizeChangeType().equals(SpacemineResizeType.SPACEMINE_CHANGE_TYPE.get())) {
						cap.setSizeDirectly(SpacemineResizeType.SPACEMINE_CHANGE_TYPE.get(), 1);
					}
				});
			}
			GulliverSize.changeSize((LivingEntity) entity, newSize);
		} else if (ModList.get().isLoaded("shrink")) {
			if (entity instanceof LivingEntity) {
				float finalNewSize1 = newSize;
				if (entity.getEntityWorld().isRemote) return;
				entity.getCapability(ShrinkAPI.SHRINK_CAPABILITY).ifPresent((iShrinkProvider) -> {
					if (finalNewSize1 == 1) iShrinkProvider.deShrink((LivingEntity) entity);
					else {
						if (!iShrinkProvider.isShrunk()) iShrinkProvider.shrink((LivingEntity) entity);
						iShrinkProvider.setScale(finalNewSize1);
					}
				});
			}
		} else if (ModList.get().isLoaded("threecore")) {
			float finalNewSize = newSize;
			entity.getCapability(CapabilitySizeChanging.SIZE_CHANGING).ifPresent((cap) -> {
				cap.startSizeChange(SpacemineResizeType.SPACEMINE_CHANGE_TYPE.get(), finalNewSize);
			});
		}
	}

	public static float getSize(Entity entity) {
		AtomicReference<Float> size = new AtomicReference<>(1f);
		if (ModList.get().isLoaded("pehkui"))
			size.set(size.get() * Spacemine.SpacemineScaleType.get().getScaleData(entity).getTargetScale());
		if (ModList.get().isLoaded("gulliver") && entity instanceof LivingEntity) {
			if (((LivingEntity) entity).getAttribute(Attributes.ENTITY_HEIGHT.get()) != null) {
				AttributeModifier modifier = ((LivingEntity) entity).getAttribute(Attributes.ENTITY_HEIGHT.get()).getModifier(uuidHeight);

				if (modifier != null) {
					float s;

					if (modifier.getOperation().equals(AttributeModifier.Operation.ADDITION))
						s = size.get() + (float) modifier.getAmount();
					else s = size.get() * (float) modifier.getAmount();

					s = 1 + s;
					s = Math.max(1f / 8, s);

					size.set(s);
				}
			}
		}
		if (ModList.get().isLoaded("shrink")) {
			if (entity instanceof LivingEntity) {
				entity.getCapability(ShrinkAPI.SHRINK_CAPABILITY).ifPresent((iShrinkProvider) -> {
					size.set(size.get() * iShrinkProvider.scale());
				});
			}
		}
		if (ModList.get().isLoaded("threecore")) {
			entity.getCapability(CapabilitySizeChanging.SIZE_CHANGING).ifPresent((cap) -> {
				float val = size.get();
				size.set(val * cap.getScale());
			});
		}
		return size.get();
	}

	/*public static void resizeForUnit(Entity entity, float amt) {
		//TODO: chiseled me integration
		if (ModList.get().isLoaded("pehkui")) {
			Spacemine.SpacemineScaleType.get().getScaleData(entity).setScale(amt);
		} else if (ModList.get().isLoaded("gullivern")) {
			GulliverSize.changeSize((LivingEntity) entity, amt);
		} else if (ModList.get().isLoaded("threecore")) {
			entity.getCapability(CapabilitySizeChanging.SIZE_CHANGING).ifPresent((cap) -> {
				cap.setSizeDirectly(SpacemineResizeType.SPACEMINE_CHANGE_TYPE.get(), amt);
			});
		} else if (ModList.get().isLoaded("shrink") && entity instanceof LivingEntity) {
			entity.getCapability(ShrinkAPI.SHRINK_CAPABILITY).ifPresent((iShrinkProvider) -> {
				if (!iShrinkProvider.isShrunk()) iShrinkProvider.shrink((LivingEntity) entity);
				iShrinkProvider.setScale(amt);
			});
		}
	}*/

	public static boolean isResizingModPresent() {
		return
				ModList.get().isLoaded("threecore") ||
						ModList.get().isLoaded("shrink") ||
						ModList.get().isLoaded("gullivern") ||
						ModList.get().isLoaded("pehkui");
	}
}