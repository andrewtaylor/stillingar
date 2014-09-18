/*
 * Copyright 2013 the original author or authors.
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

package org.brekka.stillingar.spring.snapshot;

import org.springframework.core.io.Resource;

/**
 * Placeholder used when there is no monitoring for changes to a resource. Used when reloading has been disabled.
 *
 * @author Andrew Taylor (andrew@brekka.org)
 */
public class NoopResourceMonitor implements ResourceMonitor {

    /* (non-Javadoc)
     * @see org.brekka.stillingar.spring.snapshot.ResourceMonitor#initialise(org.springframework.core.io.Resource)
     */
    @Override
    public void initialise(Resource resource) {
        // Ignore
    }

    /* (non-Javadoc)
     * @see org.brekka.stillingar.spring.snapshot.ResourceMonitor#hasChanged()
     */
    @Override
    public boolean hasChanged() {
        return false;
    }

    @Override
    public boolean canMonitor(Resource resource) {
        return true;
    }

}
