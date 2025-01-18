
import "./style.css";
import React, { ChangeEvent, useState } from "react";
import Rooms from "src/types/accommodation/rooms.interface";

const RoomRegister: React.FC<{
  room: Rooms;
  onChange: (updatedRoom: Rooms) => void;
  onDelete: () => void;
  onCopy: () => void;
}> = ({ room, onChange, onDelete, onCopy }) => {
  // state: 상태 관리 //
  const [roomNameError, setRoomNameError] = useState<string>("");
  const [roomPriceError, setRoomPriceError] = useState<string>("");
  const [roomDetailError, setDescriptionError] = useState<string>("");

  // 내가 추가한것
  const [roomMainImage, setRoomMainImage] = useState<string | null>(null);
  const [roomImages, setRoomImages] = useState<string[]>([]);
  const [roomImageError, setRoomImageError] = useState<string | null>(null);

  const handleChange = (
    event: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = event.target as HTMLInputElement | HTMLTextAreaElement;

    // 객실명과 객실 상세 정보의 글자 수 제한 및 경고 메시지 표시
    if (name === "roomName") {
      if (value.length > 45) {
        setRoomNameError("객실명은 최대 45자만 입력 가능합니다.");
      } else {
        setRoomNameError("");
        onChange({ ...room, roomName: value });
      }
    } else if (name === "roomInfo") {
      if (value.length > 1500) {
        setDescriptionError(
          "객실 상세 정보는 최대 1500자 까지만 입력 가능합니다."
        );
      } else {
        setDescriptionError("");
        onChange({ ...room, roomInfo: value });
      }
    } else if (name === "price" || name === "maxGuests") {
      const numericValue = Math.max(
        0,
        Math.min(parseInt(value) || 0, name === "price" ? 5000000 : 100000)
      );
      onChange({ ...room, [name]: numericValue });
    } else {
      onChange({ ...room, [name]: value });
    }
  };

  const handleSelectRoomImage = (file: File) => {
    const fileReader = new FileReader();
    fileReader.readAsDataURL(file);
    fileReader.onloadend = () => {
      // onChange({ ...room, roomMainImageFile: file, roomMainImagePreview: fileReader.result as string });
    };
  };

  const onPriceChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    onChange({ ...room, roomPrice: +value });
  }

  const onCheckInTimeChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    onChange({ ...room, roomCheckIn: value });
  };

  const onCheckOutTimeChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    onChange({ ...room, roomCheckOut: value });
  };

  const onRoomTotalGuestChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    onChange({ ...room, roomTotalGuest: +value });
  };

  // 내가 수정한 코드
  const onRoomMainImageChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file) {
      const imageURL = URL.createObjectURL(file); // 선택한 파일의 URL 생성
      setRoomMainImage(imageURL); // 상태 업데이트
    }
  };
  
// 내가 추가한것
const onRoomImageFilesChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
  const files = event.target.files;

  if (files) {
    const fileArray = Array.from(files);
    const imageURLs = fileArray.map((file) => URL.createObjectURL(file));

    setRoomImages(imageURLs);
  } else {
    setRoomImageError("파일 선택 중 문제가 발생했습니다.");
  }
};

  // handler:  내가 추가한것 
  const onImageDeleteHandler = (index: number) => {
    const updatedImages = [...roomImages];
    updatedImages.splice(index, 1);
    setRoomImages(updatedImages);
  };

  return (
    <div>
      <div>
        <label>
          객실명:
          <input
            type="text"
            name="roomName"
            value={room.roomName}
            onChange={handleChange}
            required
          />
        </label>
        {roomNameError && <p style={{ color: "red" }}>{roomNameError}</p>}
      </div>
      <div>
        <label>
          가격(1박기준):
          <input
            type="number"
            name="price"
            value={room.roomPrice}
            onChange={onPriceChangeHandler}
            required
            min="0"
            max="50000000"
          />
        </label>
      </div>
      
      <div  className="checkIntime-Outime-box">
      
      <div className="check">
        <div>
        <label className="checkcheck1">  
            입실 시간:
            <input
              type="time"
              name="checkInTime"
              value={room.roomCheckIn}
              onChange={onCheckInTimeChangeHandler}
              required
            />
          </label>
        </div>
        <div>
          <label>
            퇴실 시간:
            <input
              type="time"
              name="checkOutTime"
              value={room.roomCheckOut}
              onChange={onCheckOutTimeChangeHandler}
              required
            />
          </label>
        </div>
      </div>

      <div>
        <label>
          객실 상세 정보:
          <textarea
            name="roomInfo"
            value={room.roomInfo}
            onChange={handleChange}
            required
          />
        </label>
        {roomDetailError && <p style={{ color: "red" }}>{roomDetailError}</p>}
      </div>
      
      <div>
        <label>
          최대 숙박 가능 인원:
          <input
            type="number"
            name="maxGuests"
            value={room.roomTotalGuest}
            onChange={onRoomTotalGuestChangeHandler}
            required
            min="0"
            max="10"
          />
        </label>
      </div>
      
      {/* 기존 객실 대표이미지 업로드 */}
      {/* <div>
        <label>
        <div className="kikiki">
          객실 대표 이미지
          </div>
          <input type="file" onChange={onRoomMainImageChangeHandler} accept="image/*" />
          </label>
          {room.roomImages && (
            <img
              src={room.roomMainImage}
              alt="객실 대표 이미지"
              style={{ width: "100px", height: "100px" }}
            />
        )}
      </div> */}
      
      {/* 수정된 객실 대표 이미지 업로드 */}
      <div>
        <label>
          <div className="kikiki">객실 대표 이미지</div>
          <input
            type="file"
            onChange={onRoomMainImageChangeHandler}
            accept="image/*"
          />
        </label>
        {roomMainImage && (
          <img
            src={roomMainImage}
            alt="객실 대표 이미지"
            style={{ width: "100px", height: "100px", marginTop: "10px" }}
          />
        )}
      </div>

      {/* 기존객실 사진업로드 코드 */}
      {/* <div className="roomDtail">
        <label>
        <div className="kikiki">
          객실 사진 업로드</div>
          <input type="file" onChange={onRoomImageFilesChangeHandler} multiple />
        </label>
        {roomImageError && <p style={{ color: "red" }}>{roomImageError}</p>}
        <div className="image-wrapper">
          {room.roomImages.map((preview, index) => (
            <div key={index}>
              <img
                src={preview}
                alt={`Room Preview ${index}`}
                style={{ width: "100px", height: "100px" }}
              />
              <button
                type="button"
                // onClick={() => handleSelectRoomImage(room.roomMainImage[index])}
              >
                대표 이미지로 선택
              </button>
              <button type="button" 
              // onClick={() => onImageDeleteHandler(index)}
              >
                삭제
              </button>
            </div>
          ))}
        </div>
      </div> */}

      
      {/* 수정한 객실 사진업로드 코드 */}
      <div className="roomDetail">
        <label>
          <div className="kikiki">객실 사진 업로드</div>
          <input
            type="file"
            onChange={onRoomImageFilesChangeHandler}
            multiple
            accept="image/*"
          />
        </label>
        {roomImageError && <p style={{ color: "red" }}>{roomImageError}</p>}
        <div className="image-wrapper">
          {roomImages.map((preview, index) => (
            <div className="image-item" key={index}>
              <img
                src={preview}
                alt={`Room Preview ${index}`}
                className="room-preview"
              />
              <button
                type="button"
                className="delete-btn"
                onClick={() => onImageDeleteHandler(index)}
              >
                삭제
              </button>
            </div>
          ))}
        </div>
      </div>

      {/* <button type="button" onClick={onDelete}>
        삭제
      </button>
      <button type="button" onClick={onCopy}>
        복사하여 추가
      </button> */}
    </div>
    </div>

);
};

export default RoomRegister;