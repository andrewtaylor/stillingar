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

import java.util.List;

import org.brekka.stillingar.api.ConfigurationSource;
import org.brekka.stillingar.api.ValueConfigurationException;
import org.brekka.stillingar.core.conversion.ConversionManager;
import org.brekka.stillingar.core.support.BeanReflectionHelper;

/**
 * A configuration source based on the YAML Beans processor. There is currently no support for expressions, at some
 * point support may be added for YPATH - https://github.com/peterkmurphy/YPath-Specification/blob/master/ypathdoc.txt.
 *
 * @author Andrew Taylor
 */
public class JamlBeansConfigurationSource implements ConfigurationSource {

    private final ConversionManager conversionManager;
    private final BeanReflectionHelper reflectionHelper;
    
    /**
     * @param value
     */
    public JamlBeansConfigurationSource(Object bean, ConversionManager conversionManager) {
        this.conversionManager = conversionManager;
        BeanReflectionHelper helper = new BeanReflectionHelper(bean);
        this.reflectionHelper = helper;
    }

    /* (non-Javadoc)
     * @see org.brekka.stillingar.api.ConfigurationSource#isAvailable(java.lang.String)
     */
    @Override
    public boolean isAvailable(String expression) {
        throw new UnsupportedOperationException("Expressions in YAML not currently supported");
    }

    /* (non-Javadoc)
     * @see org.brekka.stillingar.api.ConfigurationSource#isAvailable(java.lang.Class)
     */
    @Override
    public boolean isAvailable(Class<?> valueType) {
        if (reflectionHelper == null) {
            throw new ValueConfigurationException(
                    "Retrieval by type is not supported when no type information is available.", valueType, null);
        }
        return reflectionHelper.isAvailable(valueType);
    }

    /* (non-Javadoc)
     * @see org.brekka.stillingar.api.ConfigurationSource#retrieve(java.lang.String, java.lang.Class)
     */
    @Override
    public <T> T retrieve(String expression, Class<T> valueType) {
        throw new UnsupportedOperationException("Expressions in YAML not currently supported");
    }

    /* (non-Javadoc)
     * @see org.brekka.stillingar.api.ConfigurationSource#retrieve(java.lang.Class)
     */
    @Override
    public <T> T retrieve(Class<T> valueType) {
        if (reflectionHelper == null) {
            throw new ValueConfigurationException(
                    "Retrieval by type is not supported when no type information is available.", valueType, null);
        }
        return reflectionHelper.findValueOf(valueType);
    }

    /* (non-Javadoc)
     * @see org.brekka.stillingar.api.ConfigurationSource#retrieveList(java.lang.String, java.lang.Class)
     */
    @Override
    public <T> List<T> retrieveList(String expression, Class<T> valueType) {
        throw new UnsupportedOperationException("Expressions in YAML not currently supported");
    }

    /* (non-Javadoc)
     * @see org.brekka.stillingar.api.ConfigurationSource#retrieveList(java.lang.Class)
     */
    @Override
    public <T> List<T> retrieveList(Class<T> valueType) {
        if (reflectionHelper == null) {
            throw new ValueConfigurationException(
                    "List retrieval by type is not supported when no type information is available.", valueType, null);
        }
        return reflectionHelper.findListOf(valueType);
    }
}
