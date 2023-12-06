#include "Application/Application.h"

Application *application = nullptr;

int main(int argc, char *argv[])
{
    application = new Application();
    application->exec();

    delete application, application = nullptr;

    return 0;
}
