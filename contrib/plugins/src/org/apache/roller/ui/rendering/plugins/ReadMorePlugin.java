/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */

package org.apache.roller.ui.rendering.plugins;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.roller.RollerException;
import org.apache.roller.pojos.WeblogEntryData;
import org.apache.roller.pojos.WebsiteData;
import org.apache.roller.business.WeblogEntryPlugin;
import org.apache.roller.util.Utilities;


/**
 * Truncates the string passed in and applies a "Read More" link if the
 * text is longer than the truncation limit.
 */
public class ReadMorePlugin implements WeblogEntryPlugin {
    
    private static Log log = LogFactory.getLog(ReadMorePlugin.class);
    
    private String name = "Read More Summary";
    private String description = "Stops entry after 250 characters and creates " +
            "a link to the full entry.";
    
    
    public ReadMorePlugin() {
        log.debug("ReadMorePlugin instantiated.");
    }
    
    
    public String getName() { 
        return name; 
    }
    
    public String getDescription() { 
        return StringEscapeUtils.escapeJavaScript(description); 
    }
    
    
    public void init(WebsiteData website) throws RollerException {
        // no-op
    }
    
    
    public String render(WeblogEntryData entry, String str) {
        
        String result = Utilities.removeHTML(str, true);
        result = Utilities.truncateText(result, 240, 260, "...");
        
        // if the result is shorter, we need to add "Read More" link
        if (result.length() < str.length()) {
            String link = "<div class=\"readMore\"><a href=\"" +
                    entry.getPermalink() + "\">Read More</a></div>";
            
            result += link;
        }
        
        return result;
    }
    
}
