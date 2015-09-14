/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright (c) 1997-2015 Oracle and/or its affiliates. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 * 
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 * 
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 * 
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.

 */
package javax.servlet.http;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * <p class="changed_added_4_0">Represents the priority of an HTTP/2 stream.
 * See the section titled "Stream Priority" in the HTTP/2 specification (RFC 7540 or any
 * RFC that obsoletes 7540).  This is a concrete class in the Servlet API due to
 * its simplicity and lack of behavior.</p>
 * 
 * @since 4.0
 */
public class Priority implements Cloneable, Serializable {
    private static final long serialVersionUID = 4216869216307748348L;
    
    private final boolean exclusive;
    private final int streamId;
    private final int weight;
    private final Optional<Priority> dependency;
    
    private Priority() {
        exclusive = false;
        streamId = 1; // Intentionally invalid
        weight = 1;
        dependency = Optional.empty();
    }

    /**
     * 
     * @param exclusive If {@code true}, the protocol must treat this stream as the
     * sole dependency of the stream represented by the priority return from
     * {@link #getDependency()}
     * @param streamId
     * @param weight 
     */
    public Priority(boolean exclusive, int streamId, int weight) {
        this.exclusive = exclusive;
        this.streamId = streamId;
        this.weight = weight;
        dependency = Optional.empty();
    }
    
    public Priority(boolean exclusive, int streamId, int weight, Optional<Priority> dependency) {
        this.exclusive = exclusive;
        this.streamId = streamId;
        this.weight = weight;
        this.dependency = dependency;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public int getStreamId() {
        return streamId;
    }

    public int getWeight() {
        return weight;
    }

    public Optional<Priority> getDependency() {
        return dependency;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.exclusive ? 1 : 0);
        hash = 53 * hash + this.streamId;
        hash = 53 * hash + this.weight;
        hash = 53 * hash + Objects.hashCode(this.dependency);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Priority other = (Priority) obj;
        if (this.exclusive != other.exclusive) {
            return false;
        }
        if (this.streamId != other.streamId) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        return Objects.equals(this.dependency, other.dependency);
    }
    
    
    
    
    
}
