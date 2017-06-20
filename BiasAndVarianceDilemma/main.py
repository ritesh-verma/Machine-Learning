import numpy
import math
import random

from numpy.polynomial import Polynomial
import matplotlib.pyplot as plt


def function(data):
    return 2 * math.cos(2.8 * data) + 7.5


begin, end = 0, 5


def create_array():
    array = []
    for i in range(0, 20):
        array.append(random.uniform(0, 5))

    return array


def create_matrix():
    mat = [[0 for i in range(20)] for j in range(100)]
    return mat


def generate_first_row(MATRIX, x_list):
    for i in range(0, 20):
        MATRIX[0][i] = function(x_list[i])

    return MATRIX


def generate_matrix(temp_mat, ARRAY):
    for i in range(1, 100):
        temp_mat.append([])
        for j in range(0, 20):
            MATRIX[i][j] = function(ARRAY[j]) + numpy.random.random()
    return temp_mat


def calculate_fit(ARRAY, MATRIX, degree):
    fig = plt.figure()
    for i in range(1, 6):
        plt.plot(*Polynomial.fit(ARRAY, MATRIX[i], degree).linspace())

    plt.plot(*Polynomial.fit(ARRAY, AVERAGE, degree).linspace(), ls="--")
    fig.savefig("fig_" + str(degree) + ".png")


def calculate_average(MATRIX):
    print len(MATRIX)
    print len(MATRIX[0])
    return numpy.mean(MATRIX, axis=0)


if __name__ == '__main__':
    ARRAY = create_array()
    MATRIX = create_matrix()
    MATRIX = generate_first_row(MATRIX, ARRAY)
    MATRIX = generate_matrix(MATRIX, ARRAY)

    AVERAGE=[]
    AVERAGE = calculate_average(MATRIX)

    for i in range(1, 6):
        calculate_fit(ARRAY, MATRIX, i)
