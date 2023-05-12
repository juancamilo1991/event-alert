import zipCodes from '../../resources/zipCodes.json';

export function isValidZipCode(area: string) {
   return Object.keys(zipCodes).some(validArea => {
        return validArea === area;
    })
}