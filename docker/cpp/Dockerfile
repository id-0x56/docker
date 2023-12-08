FROM nvidia/cuda:11.8.0-devel-ubuntu20.04

ENV DEBIAN_FRONTEND noninteractive

ARG USERNAME=ubuntu
ARG USER_UID=1000
ARG USER_GID=$USER_UID
RUN groupadd --gid $USER_GID $USERNAME \
    && useradd --uid $USER_UID --gid $USER_GID -m $USERNAME

ENV TZ=Europe/London
RUN apt update \
    && apt install --no-install-recommends -y tzdata \
    && rm -rf /var/lib/apt/lists/* \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

RUN apt update \
    && apt install --no-install-recommends -y sudo \
    && rm -rf /var/lib/apt/lists/* \
    && echo $USERNAME ALL=\(root\) NOPASSWD:ALL > /etc/sudoers.d/$USERNAME \
    && chmod 0440 /etc/sudoers.d/$USERNAME

RUN apt update \
    && apt install --no-install-recommends -y build-essential cmake pkg-config gdb \
        python3-dev python3-numpy python3-pip \
        libgtk-3-dev libgtk2.0-dev libgtkglext1-dev \
        qtbase5-dev qtbase5-dev-tools libqt5opengl5-dev \
        libavcodec-dev libavformat-dev libswscale-dev \
        libpng-dev libjpeg-dev libopenexr-dev libtiff-dev libwebp-dev \
        libgl1-mesa-dev libglu1-mesa-dev mesa-common-dev freeglut3-dev \
        git mc iproute2 curl wget zip unzip tar v4l-utils \
    && rm -rf /var/lib/apt/lists/*

RUN apt update \
    && apt install --no-install-recommends -y libgstreamer1.0-dev \
        libgstreamer-plugins-base1.0-dev libgstreamer-plugins-bad1.0-dev gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-bad gstreamer1.0-plugins-ugly \
        gstreamer1.0-libav gstreamer1.0-tools gstreamer1.0-x gstreamer1.0-alsa gstreamer1.0-gl gstreamer1.0-gtk3 gstreamer1.0-qt5 gstreamer1.0-pulseaudio gstreamer1.0-rtsp \
    && rm -rf /var/lib/apt/lists/*

ENV CUDNN_VERSION=8.8.0.121
RUN rm -rf /etc/apt/sources.list.d/* \
    && apt-key del 7fa2af80 \
    && apt-key del 3bf863cc \
    && curl -fsSL https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2004/x86_64/7fa2af80.pub | apt-key add - \
    && curl -fsSL https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2004/x86_64/3bf863cc.pub | apt-key add - \
    && echo "deb https://developer.download.nvidia.com/compute/cuda/repos/ubuntu2004/x86_64 /" > /etc/apt/sources.list.d/nvidia-cuda.list \
    && echo "deb https://developer.download.nvidia.com/compute/machine-learning/repos/ubuntu2004/x86_64 /" > /etc/apt/sources.list.d/nvidia-machine-learning.list \
    && apt update \
    && apt install --no-install-recommends -y \
        libcudnn${CUDNN_VERSION%%.*}=${CUDNN_VERSION}-1+cuda${CUDA_VERSION%.*} \
        libcudnn${CUDNN_VERSION%%.*}-dev=${CUDNN_VERSION}-1+cuda${CUDA_VERSION%.*} \
    && rm -rf /var/lib/apt/lists/*

RUN find /usr/ /var/ -name "*opencv*" | xargs rm -rf \
    && git clone --depth 1 https://github.com/opencv/opencv_contrib.git /src/opencv_contrib \
    && git clone --depth 1 https://github.com/opencv/opencv.git /src/opencv \
    && mkdir -p /src/opencv/build \
    && cd /src/opencv/build \
    && cmake \
        -D CMAKE_BUILD_TYPE=RELEASE \
        -D CMAKE_INSTALL_PREFIX=/usr/local/opencv \
        -D WITH_CUDA=ON \
        -D WITH_CUDNN=ON \
        -D OPENCV_DNN_CUDA=ON \
        -D ENABLE_FAST_MATH=ON \
        -D CUDA_FAST_MATH=ON \
        -D WITH_CUBLAS=ON \
        -D OPENCV_GENERATE_PKGCONFIG=ON \
        -D OPENCV_EXTRA_MODULES_PATH=../../opencv_contrib/modules \
        -D WITH_GTK=ON \
        -D WITH_QT=ON \
        -D WITH_FFMPEG=ON \
        -D WITH_GSTREAMER=ON \
        -D WITH_V4L=ON \
        -D WITH_OPENGL=ON \
        -D WITH_OPENCL=ON \
        -D INSTALL_C_EXAMPLES=OFF \
        -D INSTALL_PYTHON_EXAMPLES=OFF \
        -D BUILD_EXAMPLES=OFF \
        -D BUILD_PERF_TESTS=OFF \
        -D BUILD_TESTS=OFF \
        .. \
    && make -j$(nproc) \
    && make install \
    && ldconfig \
    && rm -rf /src/opencv_contrib /src/opencv

RUN git clone --depth 1 https://github.com/ultralytics/ultralytics /src/ultralytics \
    && cd /src/ultralytics \
    && pip install -e . \
    && rm -rf /root/.cache/pip \
    && chgrp -R $USERNAME /src/ultralytics \
    && chown -R $USERNAME /src/ultralytics

RUN apt update \
    && apt install --no-install-recommends -y libssl-dev libevent-dev libev-dev \
    && rm -rf /var/lib/apt/lists/* \
    && git clone --depth 1 https://github.com/CopernicaMarketingSoftware/AMQP-CPP.git /src/AMQP-CPP \
    && mkdir -p /src/AMQP-CPP/build \
    && cd /src/AMQP-CPP/build \
    && cmake \
        -D AMQP-CPP_LINUX_TCP=ON \
        .. \
    && make -j$(nproc) \
    && make install \
    && ldconfig \
    && rm -rf /src/AMQP-CPP

RUN git clone --depth 1 https://github.com/Microsoft/vcpkg.git /src/vcpkg \
    && /src/vcpkg/bootstrap-vcpkg.sh \
    && /src/vcpkg/vcpkg integrate install \
    && /src/vcpkg/vcpkg install minio-cpp

WORKDIR /var/www/cpp-app

USER $USERNAME

ENTRYPOINT [ "tail", "-f", "/dev/null" ]

# https://developer.nvidia.com/cuda-toolkit-archive
# https://developer.nvidia.com/rdp/cudnn-archive
