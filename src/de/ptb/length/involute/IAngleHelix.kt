package de.ptb.length.involute

import de.ptb.length.check.ICheckable


/**
 * @description
 * *
 * @create 2017-06-15 上午10:24
 * *
 * @email spartajet.guo@gmail.com
 */
interface IAngleHelix : IAngle, ICheckable {
    fun calculateValue(diameterReference: DiameterReference, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, diameterBase: DiameterBase, anglePressure: IAngle, angleLead: IAngle, moduleBasic: ModuleBasic, anglePressureNormal: IAngle, teethNumber: TeethNumber)

    fun calculateContradiction(diameterReference: DiameterReference, moduleTransverse: ModuleTransverse, moduleNormal: ModuleNormal, diameterBase: DiameterBase, anglePressure: IAngle, angleLead: IAngle, moduleBasic: ModuleBasic, anglePressureNormal: IAngle, teethNumber: TeethNumber, moduleAxial: ModuleAxial)
}
