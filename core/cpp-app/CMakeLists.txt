cmake_minimum_required(VERSION 3.15)
project(untitled LANGUAGES C CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall") # -Wextra -Werror

add_executable(${PROJECT_NAME})
target_sources(${PROJECT_NAME}
    PRIVATE
        ${PROJECT_SOURCE_DIR}/src/main.cpp
        ${PROJECT_SOURCE_DIR}/src/main.h
        ${PROJECT_SOURCE_DIR}/src/application/application.cpp
        ${PROJECT_SOURCE_DIR}/src/application/application.h
)
include_directories(${PROJECT_SOURCE_DIR}/include)

target_link_libraries(${PROJECT_NAME}
    PUBLIC
        ${CMAKE_DL_LIBS}
)
