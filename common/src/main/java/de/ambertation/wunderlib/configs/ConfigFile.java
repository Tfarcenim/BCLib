package de.ambertation.wunderlib.configs;

import de.ambertation.wunderlib.utils.Version;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.nio.file.Path;

import org.betterx.bclib.BCLib;
import org.betterx.bclib.platform.Services;
import org.jetbrains.annotations.Nullable;

public class ConfigFile extends AbstractConfig<ConfigFile> {
    private final File path;

    public ConfigFile(Version.ModVersionProvider versionProvider, String category) {
        this(versionProvider, versionProvider.getNamespace(), category);
    }

    public ConfigFile(Version.ModVersionProvider versionProvider, String basePath, String category) {
        super(versionProvider, basePath, category);
        final Path dir = Services.PLATFORM.getConfigDir().resolve(basePath);
        path = dir.resolve(category + ".json").toFile();

        if (!dir.toFile().exists()) dir.toFile().mkdirs();
        loadFromDisc();
    }

    @Override
    protected boolean isReadOnly() {
        return false;
    }

    @Override
    protected @Nullable JsonObject loadRootElement() {
        if (path.exists()) {
            try (Reader reader = new FileReader(path)) {
                return AbstractConfig.JSON_BUILDER.fromJson(reader, JsonElement.class).getAsJsonObject();
            } catch (Exception ex) {
                BCLib.LOG.error("Unable to open Config File at '{}'.", path, ex);
            }
        }
        return null;
    }

    @Override
    protected boolean saveRootElement(String root) {
        try (FileWriter jsonWriter = new FileWriter(path)) {
            jsonWriter.write(root);
            jsonWriter.flush();
        } catch (IOException ex) {
            BCLib.LOG.error("Unable to store Config File at '{}'.", path, ex);
            return false;
        }

        return true;
    }
}
