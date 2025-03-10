/*
 *  Copyright (c) 2023-2025, Agents-Flex (fuhai999@gmail.com).
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.agentsflex.core.image;

import com.agentsflex.core.util.Metadata;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageResponse extends Metadata {
    private List<Image> images;
    private boolean error;
    private String errorMessage;

    public static ImageResponse error(String errMessage) {
        ImageResponse imageResponse = new ImageResponse();
        imageResponse.setError(true);
        imageResponse.setErrorMessage(errMessage);
        return imageResponse;
    }

    public void addImage(String url) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }

        this.images.add(Image.ofUrl(url));
    }

    public void addImage(byte[] bytes) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }

        this.images.add(Image.ofBytes(bytes));
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
            "images=" + images +
            ", error=" + error +
            ", errorMessage='" + errorMessage + '\'' +
            ", metadataMap=" + metadataMap +
            '}';
    }
}
