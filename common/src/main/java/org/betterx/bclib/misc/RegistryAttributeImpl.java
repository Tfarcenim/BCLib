package org.betterx.bclib.misc;

import net.minecraft.resources.ResourceKey;

import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class RegistryAttributeImpl implements RegistryAttributeHolder {
    private static final Map<ResourceKey<?>, RegistryAttributeHolder> HOLDER_MAP = new ConcurrentHashMap<>();

    public static RegistryAttributeHolder getHolder(ResourceKey<?> registryKey) {
        return HOLDER_MAP.computeIfAbsent(registryKey, key -> new RegistryAttributeImpl());
    }

    private final EnumSet<RegistryAttribute> attributes = EnumSet.noneOf(RegistryAttribute.class);

    private RegistryAttributeImpl() {
    }

    @Override
    public RegistryAttributeHolder addAttribute(RegistryAttribute attribute) {
        attributes.add(attribute);
        return this;
    }

    @Override
    public boolean hasAttribute(RegistryAttribute attribute) {
        return attributes.contains(attribute);
    }
}
