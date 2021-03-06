package nl.tudelft.oopp.group54.controllers.lectures;

import java.util.Date;
import java.util.Map;

public interface LectureService {
    Map<String, Object> createNewLecture(Date startTime, String lectureName);

    Map<String, Object> joinOngoingLecture(Integer lectureId, String roleCode, String userName);

    Map<String, Object> getLectureMetadata(Integer lectureId);

    Map<String, Object> postLectureFeedback(Integer lectureID,
                                            String userId,
                                            Integer lectureFeedbackId);

    Map<String, Object> getLectureFeedback(Integer lectureID, String userId);
}
