/*
 * Copyright (C) 2020 panpf <panpfpanpf@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.panpf.tools4j.net;

import org.jetbrains.annotations.NotNull;

public class City {

    @NotNull
    private final String id;
    @NotNull
    private final String name;
    @NotNull
    private final String ip;

    public City(@NotNull String id, @NotNull String name, @NotNull String ip) {
        this.id = id;
        this.name = name;
        this.ip = ip;
    }

    @NotNull
    public String getId() {
        return id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getIp() {
        return ip;
    }
}
