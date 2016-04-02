package openmods.depcheck;

import java.io.File;

import openmods.depcheck.utils.Field;

import org.objectweb.asm.commons.Method;

import com.google.common.collect.*;

public class DependencyResolveResult {

    public static class MissingDependencies {
        public final Multimap<String, String> missingClasses = HashMultimap.create();

        public final Multimap<String, Method> missingMethods = HashMultimap.create();

        public final Multimap<String, Field> missingFields = HashMultimap.create();
    }

    public final File jarFile;

    public final Table<String, String, MissingDependencies> missingDependencies = HashBasedTable.create();

    public DependencyResolveResult(File jarFile) {
        this.jarFile = jarFile;
    }

    public MissingDependencies getOrCreate(String modId, String version) {
        MissingDependencies result = missingDependencies.get(modId, version);
        if (result == null) {
            result = new MissingDependencies();
            missingDependencies.put(modId, version, result);
        }

        return result;
    }

}