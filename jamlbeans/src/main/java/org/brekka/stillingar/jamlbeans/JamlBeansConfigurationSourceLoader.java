/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.brekka.stillingar.jamlbeans;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;

import org.brekka.stillingar.api.ConfigurationException;
import org.brekka.stillingar.api.ConfigurationSource;
import org.brekka.stillingar.api.ConfigurationSourceLoader;
import org.brekka.stillingar.core.conversion.ConversionManager;
import org.brekka.stillingar.core.conversion.TemporalAdapter;
import org.brekka.stillingar.core.conversion.TypeConverter;
import org.brekka.stillingar.core.conversion.TypeConverterListBuilder;
import org.brekka.stillingar.core.dom.DOMConfigurationSourceLoader;

import com.esotericsoftware.yamlbeans.YamlReader;

/**
 * Loader for {@link JamlBeansConfigurationSource} instances
 *
 * @author Andrew Taylor
 */
public class JamlBeansConfigurationSourceLoader implements ConfigurationSourceLoader {

    
    private final Class<?> documentClass;
    
    private final ConversionManager conversionManager;
    
    public JamlBeansConfigurationSourceLoader(Class<?> documentClass) {
        this(documentClass, null);
    }
    
    /**
     * @param objectMapper
     */
    public JamlBeansConfigurationSourceLoader(Class<?> documentClass,
            ConversionManager conversionManager) {
        this.documentClass = documentClass;
        this.conversionManager = conversionManager != null ? conversionManager : new ConversionManager(
                prepareConverters());
    }


    /* (non-Javadoc)
     * @see org.brekka.stillingar.api.ConfigurationSourceLoader#parse(java.io.InputStream, java.nio.charset.Charset)
     */
    @Override
    public ConfigurationSource parse(InputStream sourceStream, Charset encoding) throws ConfigurationException,
            IOException {
        YamlReader reader = new YamlReader(new InputStreamReader(sourceStream, encoding));
        Object object = reader.read(documentClass);
        JamlBeansConfigurationSource source = new JamlBeansConfigurationSource(object, conversionManager);
        return source;
    }
    
    
    public static List<TypeConverter<?>> prepareConverters() {
        TemporalAdapter temporalAdapter = new TemporalAdapter();
        return new TypeConverterListBuilder(DOMConfigurationSourceLoader.prepareConverters(temporalAdapter))
            .toList();
    }
}
