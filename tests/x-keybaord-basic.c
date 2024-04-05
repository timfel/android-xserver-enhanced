// gcc x-keybaord-basic.c -lX11 -lXtst -o x-keybaord-basic
#include <X11/Xlib.h>
#include <X11/keysym.h>
#include <X11/extensions/XTest.h>
#include <stdio.h>
#include <unistd.h>

void simulate_key_press(Display *display, KeySym keysym) {
    KeyCode keycode = XKeysymToKeycode(display, keysym);
    XTestFakeKeyEvent(display, keycode, True, CurrentTime);
    XTestFakeKeyEvent(display, keycode, False, CurrentTime);
    XFlush(display);
}

int main() {
    Display *display = XOpenDisplay(NULL);
    if (display == NULL) {
        fprintf(stderr, "Failed to open display\n");
        return 1;
    }

    // Get screen depth and color masks
    int screen = DefaultScreen(display);
    int depth = DefaultDepth(display, screen);
    Visual *visual = DefaultVisual(display, screen);
    printf("Screen depth: %d bits per pixel\n", depth);
    printf("Red mask: 0x%08lx\n", visual->red_mask);
    printf("Green mask: 0x%08lx\n", visual->green_mask);
    printf("Blue mask: 0x%08lx\n", visual->blue_mask);

    // Test case 1: Simulate key press events for "Hello"
    simulate_key_press(display, XK_H);
    simulate_key_press(display, XK_e);
    simulate_key_press(display, XK_l);
    simulate_key_press(display, XK_l);
    simulate_key_press(display, XK_o);

    // Verify the expected result
    // You can manually observe the typed text or use additional libraries
    // to capture and verify the result programmatically

    // Wait for a short duration
    sleep(1);

    // Test case 2: Simulate key press events for "World"
    simulate_key_press(display, XK_W);
    simulate_key_press(display, XK_o);
    simulate_key_press(display, XK_r);
    simulate_key_press(display, XK_l);
    simulate_key_press(display, XK_d);

    // Verify the expected result
    // You can manually observe the typed text or use additional libraries
    // to capture and verify the result programmatically

    // Wait for a short duration
    sleep(1);

    XCloseDisplay(display);
    return 0;
}