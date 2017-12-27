package com.zmcsoft.apsp.client.sdk.drivers.printer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouhao
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MillimeterPaper implements Paper {
    private int width;

    private int height;
}