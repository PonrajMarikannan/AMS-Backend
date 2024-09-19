package com.raj.ams.service;


import java.io.File;

public interface FaceRecognitionService {
    boolean verifyFaces(File referenceImageFile, File capturedImageFile);
}
